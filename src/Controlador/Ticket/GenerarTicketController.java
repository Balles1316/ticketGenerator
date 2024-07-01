package Controlador.Ticket;

import Modelo.ServiciosModel;
import Modelo.TicketModel;
import Vista.Servicio.InsertarServiciosView;
import Vista.Ticket.GenerarTicketView;

public class GenerarTicketController {
    private final GenerarTicketView vista;
    private final TicketModel modelo;

    public GenerarTicketController(GenerarTicketView vista, TicketModel modelo) {
        this.vista = vista;
        this.modelo = modelo;
    }
}
