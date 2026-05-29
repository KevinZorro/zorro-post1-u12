package com.empresa.pedidos.adaptadores.rest;

import com.empresa.pedidos.aplicacion.ServicioPedidosLegacy;
import com.empresa.pedidos.dominio.Pedido;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {
    private final ServicioPedidosLegacy servicioPedidos;

    public PedidoController(ServicioPedidosLegacy servicioPedidos) {
        this.servicioPedidos = servicioPedidos;
    }

    @PostMapping
    public ResponseEntity<Pedido> crearPedido(@RequestBody Pedido pedido) {
        servicioPedidos.procesarPedido(pedido);
        return ResponseEntity.ok(pedido);
    }
}
