package com.empresa.pedidos.infraestructura.notificaciones;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.empresa.pedidos.dominio.Pedido;
import com.empresa.pedidos.dominio.puertos.ServicioNotificacion;

@Primary
@Service
public class NotificacionLog implements ServicioNotificacion {
    private static final Logger log = LoggerFactory.getLogger(NotificacionLog.class);

    @Override
    public void notificarPedidoProcesado(Pedido pedido) {
        log.info("Pedido {} procesado con costo {}", pedido.getId(), pedido.getCosto());
    }
}
