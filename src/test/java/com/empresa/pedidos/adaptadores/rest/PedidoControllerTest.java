package com.empresa.pedidos.adaptadores.rest;

import com.empresa.pedidos.adaptadores.facade.FachadaPedidos;
import com.empresa.pedidos.dominio.Pedido;
import com.empresa.pedidos.dominio.TipoPedido;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class PedidoControllerTest {

    @Mock
    private FachadaPedidos fachada;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void crearDevuelvePedidoProcesado() {
        Pedido pedido = new Pedido();
        pedido.setTipo(TipoPedido.ESTANDAR);
        pedido.setSubtotal(100.0);
        pedido.setEmailCliente("cliente@empresa.com");
        pedido.setCosto(110.0);

        when(fachada.crearPedido(any(Pedido.class))).thenReturn(pedido);

        Pedido result = new PedidoController(fachada).crear(pedido).getBody();

        assertEquals("cliente@empresa.com", result.getEmailCliente());
        assertEquals(110.0, result.getCosto());
    }
}
