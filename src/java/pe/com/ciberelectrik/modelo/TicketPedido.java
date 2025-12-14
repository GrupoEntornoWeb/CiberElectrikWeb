package pe.com.ciberelectrik.modelo;
 
import java.util.Date;

public class TicketPedido {
    private int numero;
    private Date fecha;
    private boolean estado;
    private Cliente cliente;
    private Empleado empleado;
    private double subtotal;

    public TicketPedido() {
    }

    public TicketPedido(int numero, Date fecha, boolean estado, Cliente cliente, Empleado empleado, double subtotal) {
        this.numero = numero;
        this.fecha = fecha;
        this.estado = estado;
        this.cliente = cliente;
        this.empleado = empleado;
        this.subtotal = subtotal;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }
    
}
