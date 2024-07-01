package Controlador.Ticket;

import Modelo.TicketModel;
import Vista.Ticket.EliminarTicketView;

public class EliminarTicketController {
    private final EliminarTicketView vista;
    private final TicketModel modelo;
    private String nombreSeleccionado;

    public EliminarTicketController(EliminarTicketView vista, TicketModel modelo) {
        this.vista = vista;
        this.modelo = modelo;
    }
}
