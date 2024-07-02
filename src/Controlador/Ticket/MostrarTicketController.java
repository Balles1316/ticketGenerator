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

        try{
            cargarTicket();
        } catch (Exception ex) {
            vista.mostrarMensaje("Error al cargar los clientes.");
        }
    }

    private void cargarTicket() {
        modelo.cargarTickets(vista.getTablaTicket(), vista.getModeloTabla());
    }
}
