package com.empresa.pedidos.aplicacion;

import com.empresa.pedidos.dominio.EstadoPedido;
import com.empresa.pedidos.dominio.Pedido;
import com.empresa.pedidos.dominio.TipoPedido;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired; 

// ANTES: ServicioPedidosLegacy — todo en un solo método
@Service
public class ServicioPedidosLegacy {
 @Autowired private PedidoRepository repo;
 @Autowired private JavaMailSender mail;
 public void procesarPedido(Pedido pedido) {
 // Lógica mezclada: tipo, cálculo, persistencia y notificación
 if (pedido.getTipo() == TipoPedido.ESTANDAR) {
 pedido.setCosto(pedido.getSubtotal() * 1.1);
 } else if (pedido.getTipo() == TipoPedido.EXPRESS) {
 pedido.setCosto(pedido.getSubtotal() * 1.3);
 } else if (pedido.getTipo() == TipoPedido.INTERNACIONAL) {
 pedido.setCosto(pedido.getSubtotal() * 1.5 + 25.0);
 }
 pedido.setEstado(EstadoPedido.PROCESADO);
 repo.save(pedido);
 // Notificación acoplada directamente
 mail.send(crearMensaje(pedido));
 }

	private Pedido crearMensaje(Pedido pedido) {
		// Minimal adapter: return the Pedido so JavaMailSender can handle it
		return pedido;
	}
}
