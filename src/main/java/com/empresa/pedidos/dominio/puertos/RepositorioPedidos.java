package com.empresa.pedidos.dominio.puertos;

import com.empresa.pedidos.dominio.Pedido;
import java.util.List;
import java.util.Optional;

public interface RepositorioPedidos {
    Pedido guardar(Pedido pedido);

    // Listar pedidos existentes
    List<Pedido> listar();

    // Buscar pedido por id
    Optional<Pedido> buscarPorId(Long id);
}
