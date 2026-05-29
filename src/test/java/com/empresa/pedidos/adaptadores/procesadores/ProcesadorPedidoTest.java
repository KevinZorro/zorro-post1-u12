package com.empresa.pedidos.adaptadores.procesadores;

import com.empresa.pedidos.dominio.EstadoPedido;
import com.empresa.pedidos.dominio.Pedido;
import com.empresa.pedidos.dominio.TipoPedido;
import com.empresa.pedidos.dominio.puertos.ProcesadorPedido;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProcesadorPedidoTest {

    @Test
    void procesadorEstandarCalculaCostoYMarcaProcesado() {
        ProcesadorPedido processor = new ProcesadorPedidoEstandar();
        Pedido pedido = new Pedido();
        pedido.setSubtotal(100.0);
        pedido.setTipo(TipoPedido.ESTANDAR);

        processor.procesar(pedido);

        assertEquals(110.0, pedido.getCosto(), 0.00001);
        assertEquals(EstadoPedido.PROCESADO, pedido.getEstado());
    }

    @Test
    void procesadorExpressCalculaCostoYMarcaProcesado() {
        ProcesadorPedido processor = new ProcesadorPedidoExpress();
        Pedido pedido = new Pedido();
        pedido.setSubtotal(100.0);
        pedido.setTipo(TipoPedido.EXPRESS);

        processor.procesar(pedido);

        assertEquals(130.0, pedido.getCosto(), 0.00001);
        assertEquals(EstadoPedido.PROCESADO, pedido.getEstado());
    }

    @Test
    void procesadorInternacionalCalculaCostoYMarcaProcesado() {
        ProcesadorPedido processor = new ProcesadorPedidoInternacional();
        Pedido pedido = new Pedido();
        pedido.setSubtotal(100.0);
        pedido.setTipo(TipoPedido.INTERNACIONAL);

        processor.procesar(pedido);

        assertEquals(175.0, pedido.getCosto(), 0.00001);
        assertEquals(EstadoPedido.PROCESADO, pedido.getEstado());
    }

    @Test
    void factoryReturnsCorrectProcessorAndThrowsWhenMissing() {
        var factory = new ProcesadorPedidoFactory(List.of(
                new ProcesadorPedidoEstandar(),
                new ProcesadorPedidoExpress(),
                new ProcesadorPedidoInternacional()
        ));

        assertTrue(factory.obtener(TipoPedido.ESTANDAR) instanceof ProcesadorPedidoEstandar);
        assertTrue(factory.obtener(TipoPedido.EXPRESS) instanceof ProcesadorPedidoExpress);
        assertTrue(factory.obtener(TipoPedido.INTERNACIONAL) instanceof ProcesadorPedidoInternacional);

        var emptyFactory = new ProcesadorPedidoFactory(List.of());
        assertThrows(IllegalArgumentException.class, () -> emptyFactory.obtener(TipoPedido.ESTANDAR));
    }
}
