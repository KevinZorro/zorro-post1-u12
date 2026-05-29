package com.empresa.pedidos.infraestructura.persistencia;

import com.empresa.pedidos.dominio.Pedido;
import com.empresa.pedidos.dominio.puertos.RepositorioPedidos;
import org.springframework.stereotype.Repository;

@Repository
public class RepositorioPedidosJpa implements RepositorioPedidos {
    private final PedidoJpaRepository repository;

    public RepositorioPedidosJpa(PedidoJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public Pedido guardar(Pedido pedido) {
        return repository.save(pedido);
    }

    @Override
    public java.util.List<Pedido> listar() {
        return repository.findAll();
    }

    @Override
    public java.util.Optional<Pedido> buscarPorId(Long id) {
        return repository.findById(id);
    }
}
