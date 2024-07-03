package Objeto;

public class Ticket {

    private int numeroTicket;
    private String servicio;
    private String producto;
    private int cantidad;
    private double precioConIVA;
    private String cliente;
    private String metodoPago;
    private String fecha;

    public Ticket(int numeroTicket, String servicio,String producto, int cantidad, double precioConIVA, String cliente, String metodoPago,String fecha) {
        this.numeroTicket = numeroTicket;
        this.servicio = servicio;
        this.producto = producto;
        this.cantidad = cantidad;
        this.precioConIVA = precioConIVA;
        this.cliente = cliente;
        this.metodoPago = metodoPago;
        this.fecha = fecha;
    }

    public int getNumeroTicket() {
        return numeroTicket;
    }

    public void setNumeroTicket(int numeroTicket) {
        this.numeroTicket = numeroTicket;
    }

    public String getServicio() {
        return servicio;
    }

    public void setServicio(String servicio) {
        this.servicio = servicio;
    }

    public String getProducto() {return producto;}

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecioConIVA() {
        return precioConIVA;
    }

    public void setPrecioConIVA(double precioConIVA) {
        this.precioConIVA = precioConIVA;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fechaHora) {
        this.fecha = fechaHora;
    }
}