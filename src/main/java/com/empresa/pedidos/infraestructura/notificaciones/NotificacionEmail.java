package com.empresa.pedidos.infraestructura.notificaciones;

import com.empresa.pedidos.dominio.puertos.ServicioNotificacion;
import org.springframework.stereotype.Component;
import org.springframework.context.event.EventListener;
import com.empresa.pedidos.dominio.PedidoProcesadoEvent;

// Listener de email
@Component
public class NotificacionEmail implements ServicioNotificacion {
 @EventListener
 @Override
 public void notificar(PedidoProcesadoEvent evento) {
 // Lógica de envío de email
 System.out.println("Email enviado para pedido: "
 + evento.pedido().getId());
 }
}