package com.empresa.pedidos.dominio.puertos;

import com.empresa.pedidos.dominio.PedidoProcesadoEvent;

// Puerto de notificación
public interface ServicioNotificacion {
 void notificar(PedidoProcesadoEvent evento);
}