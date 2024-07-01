package Controlador.Ticket;

import Modelo.ServiciosModel;
import Modelo.TicketModel;
import Vista.Servicio.MostrarServiciosView;
import Vista.Ticket.MostrarTicketView;

public class MostrarTicketController {
    private final MostrarTicketView vista;
    private final TicketModel modelo;

    public MostrarTicketController(MostrarTicketView vista, TicketModel modelo) {
        this.vista = vista;
        this.modelo = modelo;
    }
}
