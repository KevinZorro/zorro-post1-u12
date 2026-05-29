package com.empresa.pedidos.infraestructura.notificaciones;

import com.empresa.pedidos.dominio.Pedido;
import com.empresa.pedidos.dominio.PedidoProcesadoEvent;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class NotificacionTest {

    @Test
    void notificacionEmailManejaEventoSinError() {
        NotificacionEmail email = new NotificacionEmail();
        Pedido pedido = new Pedido();
        pedido.setId(1L);
        pedido.setEmailCliente("test@empresa.com");

        assertDoesNotThrow(() -> email.notificar(new PedidoProcesadoEvent(pedido)));
    }

    @Test
    void notificacionLogManejaEventoSinError() {
        NotificacionLog notificacionLog = new NotificacionLog();
        Pedido pedido = new Pedido();
        pedido.setCosto(99.0);
        pedido.setId(2L);

        assertDoesNotThrow(() -> notificacionLog.notificar(new PedidoProcesadoEvent(pedido)));
    }
}
