package com.empresa.pedidos.aplicacion;

import com.empresa.pedidos.dominio.Pedido;
import com.empresa.pedidos.dominio.PedidoProcesadoEvent;
import com.empresa.pedidos.dominio.puertos.ServicioNotificacion;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.mockito.Mockito.verify;

class JavaMailSenderTest {

    @Mock
    private ServicioNotificacion notificacion1;
    @Mock
    private ServicioNotificacion notificacion2;

    private JavaMailSender sender;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        sender = new JavaMailSender(List.of(notificacion1, notificacion2));
    }

    @Test
    void sendPedidoNotifiesAllServices() {
        Pedido pedido = new Pedido();
        pedido.setEmailCliente("cliente@empresa.com");
        pedido.setSubtotal(50.0);

        sender.send(pedido);

        verify(notificacion1).notificar(new PedidoProcesadoEvent(pedido));
        verify(notificacion2).notificar(new PedidoProcesadoEvent(pedido));
    }

    @Test
    void sendNonPedidoObjectDoesNotThrow() {
        sender.send("un mensaje de prueba");
    }
}
