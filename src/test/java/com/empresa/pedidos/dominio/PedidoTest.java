package com.empresa.pedidos.dominio;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PedidoTest {

    @Test
    void defaultEstadoShouldBeCreadoAndGettersSettersWork() {
        Pedido pedido = new Pedido();

        assertEquals(EstadoPedido.CREADO, pedido.getEstado());

        pedido.setEmailCliente("cliente@empresa.com");
        pedido.setSubtotal(150.0);
        pedido.setTipo(TipoPedido.EXPRESS);
        pedido.setCosto(195.0);

        assertEquals("cliente@empresa.com", pedido.getEmailCliente());
        assertEquals(150.0, pedido.getSubtotal());
        assertEquals(TipoPedido.EXPRESS, pedido.getTipo());
        assertEquals(195.0, pedido.getCosto());
    }
}
