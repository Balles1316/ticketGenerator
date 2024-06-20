package Modelo;

public class Ticket {

    private String txtNumeroTicket;
    private String txtCantidad;
    private String txtPrecioConIVA;
    private String servicio;

    public Ticket() {
        // Constructor por defecto
    }

    public Ticket(String txtNumeroTicket, String txtCantidad, String txtPrecioConIVA, String servicio) {
        this.txtNumeroTicket = txtNumeroTicket;
        this.txtCantidad = txtCantidad;
        this.txtPrecioConIVA = txtPrecioConIVA;
        this.servicio = servicio;
    }

    public String getTxtNumeroTicket() {
        return txtNumeroTicket;
    }

    public void setTxtNumeroTicket(String txtNumeroTicket) {
        this.txtNumeroTicket = txtNumeroTicket;
    }

    public String getTxtCantidad() {
        return txtCantidad;
    }

    public void setTxtCantidad(String txtCantidad) {
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
