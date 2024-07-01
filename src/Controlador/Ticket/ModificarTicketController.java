package Controlador.Ticket;

import Modelo.ServiciosModel;
import Modelo.TicketModel;
import Objeto.Servicio;
import Objeto.Ticket;
import Vista.Servicio.ModificarServiciosView;
import Vista.Ticket.ModificarTicketView;

import javax.swing.event.TableModelEvent;
import java.util.ArrayList;
import java.util.List;

public class ModificarTicketController {
    private final ModificarTicketView vista;
    private final TicketModel modelo;
    private final List<Ticket> tickets;

    public ModificarTicketController(ModificarTicketView vista, TicketModel modelo) {
        this.vista = vista;
        this.modelo = modelo;
        this.tickets = new ArrayList<>();
    }

}