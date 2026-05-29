package com.empresa.pedidos.dominio.puertos;

import com.empresa.pedidos.dominio.Pedido;

public interface ServicioNotificacion {
    void notificarPedidoProcesado(Pedido pedido);
}
