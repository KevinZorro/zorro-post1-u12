package com.empresa.pedidos.aplicacion;

import com.empresa.pedidos.dominio.Pedido;
import com.empresa.pedidos.dominio.puertos.RepositorioPedidos;
import org.springframework.stereotype.Component;

@Component
public class PedidoRepository {
    private final RepositorioPedidos repo;

    public PedidoRepository(RepositorioPedidos repo) {
        this.repo = repo;
    }

    public Pedido save(Pedido pedido) {
        return repo.guardar(pedido);
    }
}
