package com.empresa.pedidos.aplicacion;

import com.empresa.pedidos.dominio.Pedido;
import com.empresa.pedidos.dominio.PedidoProcesadoEvent;
import com.empresa.pedidos.dominio.puertos.ServicioNotificacion;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JavaMailSender {
    private final List<ServicioNotificacion> notificaciones;

    public JavaMailSender(List<ServicioNotificacion> notificaciones) {
        this.notificaciones = notificaciones;
    }

    /**
     * Accepts any message object; if it's a Pedido, delegate to all ServicioNotificacion beans.
     */
    public void send(Object message) {
        if (message instanceof Pedido pedido) {
            var evento = new PedidoProcesadoEvent(pedido);
            for (ServicioNotificacion s : notificaciones) {
                s.notificar(evento);
            }
        } else {
            System.out.println("[JavaMailSender] send: " + message);
        }
    }
}
