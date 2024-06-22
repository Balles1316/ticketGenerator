package Modelo;

public class Ticket {

    private int txtNumeroTicket;
    private int txtCantidad;
    private String txtPrecioConIVA;
    private String servicio;

    public Ticket() {
        // Constructor por defecto
    }

    public Ticket(int txtNumeroTicket, String servicio , int txtCantidad, String txtPrecioConIVA ) {
        this.txtNumeroTicket = txtNumeroTicket;
        this.txtCantidad = txtCantidad;
        this.txtPrecioConIVA = txtPrecioConIVA;
        this.servicio = servicio;
    }

    public int getTxtNumeroTicket() {
        return txtNumeroTicket;
    }

    public void setTxtNumeroTicket(int txtNumeroTicket) {
        this.txtNumeroTicket = txtNumeroTicket;
    }

    public int getTxtCantidad() {
        return txtCantidad;
    }

    public void setTxtCantidad(int txtCantidad) {
        this.txtCantidad = txtCantidad;
    }

    public String getTxtPrecioConIVA() {
        return txtPrecioConIVA;
    }

    public void setTxtPrecioConIVA(String txtPrecioConIVA) {
        this.txtPrecioConIVA = txtPrecioConIVA;
    }

    public String getServicio() {
        return servicio;
    }

    public void setServicio(String servicio) {
        this.servicio = servicio;
    }
}
