package pe.com.ciberelectrik.modelo;

public class DetalleTicketPedido {
    private int numerodetalle;
    private int cantidad;
    private double precio;
    private TicketPedido ticketpedido;
    private Producto producto;

    public DetalleTicketPedido() {
    }

    
    public DetalleTicketPedido(int numerodetalle, int cantidad, double precio, TicketPedido ticketpedido, Producto producto) {
        this.numerodetalle = numerodetalle;
        this.cantidad = cantidad;
        this.precio = precio;
        this.ticketpedido = ticketpedido;
        this.producto = producto;
    }

    public int getNumerodetalle() {
        return numerodetalle;
    }

    public void setNumerodetalle(int numerodetalle) {
        this.numerodetalle = numerodetalle;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public TicketPedido getTicketpedido() {
        return ticketpedido;
    }

    public void setTicketpedido(TicketPedido ticketpedido) {
        this.ticketpedido = ticketpedido;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }
    
    
    
}