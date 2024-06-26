package Modelo;

public class Ticket {

    private int numeroTicket;
    private String servicio;
    private int cantidad;
    private double precioConIVA;
    private String cliente;
    private String metodoPago;

    public Ticket(int numeroTicket, String servicio, int cantidad, double precioConIVA, String cliente, String metodoPago) {
        this.numeroTicket = numeroTicket;
        this.servicio = servicio;
        this.cantidad = cantidad;
        this.precioConIVA = precioConIVA;
        this.cliente = cliente;
        this.metodoPago = metodoPago;
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
}