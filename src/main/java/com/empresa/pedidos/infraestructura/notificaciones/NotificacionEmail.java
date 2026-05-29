package com.empresa.pedidos.infraestructura.notificaciones;

import com.empresa.pedidos.dominio.Pedido;
import com.empresa.pedidos.dominio.puertos.ServicioNotificacion;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Primary
@Component
public class NotificacionEmail implements ServicioNotificacion {

    @Override
    public void notificarPedidoProcesado(Pedido pedido) {
        System.out.println("[Email] Pedido procesado: " + pedido.getId() + " para " + pedido.getEmailCliente());
    }
}
