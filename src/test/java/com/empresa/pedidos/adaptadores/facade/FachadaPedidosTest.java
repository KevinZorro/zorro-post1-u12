package com.empresa.pedidos.adaptadores.facade;

import com.empresa.pedidos.dominio.Pedido;
import com.empresa.pedidos.dominio.PedidoProcesadoEvent;
import com.empresa.pedidos.dominio.TipoPedido;
import com.empresa.pedidos.dominio.puertos.RepositorioPedidos;
import com.empresa.pedidos.dominio.puertos.ProcesadorPedido;
import com.empresa.pedidos.adaptadores.procesadores.ProcesadorPedidoFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.context.ApplicationEventPublisher;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class FachadaPedidosTest {

    @Mock
    private ProcesadorPedidoFactory factory;
    @Mock
    private RepositorioPedidos repositorio;
    @Mock
    private ApplicationEventPublisher publisher;
    @Mock
    private ProcesadorPedido procesador;

    private FachadaPedidos fachada;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        fachada = new FachadaPedidos(factory, repositorio, publisher);
    }

    @Test
    void crearPedidoProcesaGuardaYPublicaEvento() {
        Pedido pedido = new Pedido();
        pedido.setTipo(TipoPedido.ESTANDAR);
        pedido.setSubtotal(100.0);
        pedido.setEmailCliente("test@empresa.com");

        when(factory.obtener(TipoPedido.ESTANDAR)).thenReturn(procesador);
        when(repositorio.guardar(any(Pedido.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Pedido result = fachada.crearPedido(pedido);

        verify(procesador).procesar(pedido);
        verify(repositorio).guardar(pedido);
        ArgumentCaptor<PedidoProcesadoEvent> captor = ArgumentCaptor.forClass(PedidoProcesadoEvent.class);
        verify(publisher).publishEvent(captor.capture());
        assertSame(result, captor.getValue().pedido());
    }

    @Test
    void buscarPorIdDelegatesALaRepo() {
        when(repositorio.buscarPorId(10L)).thenReturn(Optional.of(new Pedido()));

        Optional<Pedido> result = fachada.buscarPorId(10L);

        assertTrue(result.isPresent());
        verify(repositorio).buscarPorId(10L);
    }
}
