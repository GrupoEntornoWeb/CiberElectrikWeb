package pe.com.ciberelectrik.dao;

import java.util.List;
import pe.com.ciberelectrik.modelo.DetalleTicketPedido;
import pe.com.ciberelectrik.modelo.TicketPedido;

public interface TicketPedidoDAO {
    List<TicketPedido> findAll();
    List<TicketPedido> findAllCustom();
    boolean add(TicketPedido obj);
    boolean enable(TicketPedido obj);
    boolean disable(TicketPedido obj);
    int setNumber();
    int getNumber();
    boolean addDetails(DetalleTicketPedido obj);
}

