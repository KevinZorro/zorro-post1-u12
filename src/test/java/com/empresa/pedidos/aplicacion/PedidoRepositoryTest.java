package com.empresa.pedidos.aplicacion;

import com.empresa.pedidos.dominio.Pedido;
import com.empresa.pedidos.dominio.puertos.RepositorioPedidos;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class PedidoRepositoryTest {

    @Mock
    private RepositorioPedidos repo;

    private PedidoRepository pedidoRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        pedidoRepository = new PedidoRepository(repo);
    }

    @Test
    void saveDelegatesToRepositorioPedidos() {
        Pedido pedido = new Pedido();
        when(repo.guardar(any(Pedido.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Pedido saved = pedidoRepository.save(pedido);

        assertSame(pedido, saved);
    }
}
