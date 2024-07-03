package Controlador.Ticket;

import Modelo.TicketModel;
import Vista.Ticket.MostrarTicketView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MostrarTicketController {
    private final MostrarTicketView vista;
    private final TicketModel modelo;

    public MostrarTicketController(MostrarTicketView vista, TicketModel modelo) {
        this.vista = vista;
        this.modelo = modelo;

        try {
            cargarTicket();
        } catch (Exception ex) {
            vista.mostrarMensaje("Error al cargar los clientes.");
        }

        vista.getBtnBuscarTicket().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarTickets();
            }
        });
    }

    private void cargarTicket() {
        modelo.cargarTickets(vista.getTablaTicket(), vista.getModeloTabla());
    }

    private void buscarTickets() {
        String numeroTicket = vista.getTxtNumeroTicket().getText();
        String servicio = vista.getTxtServicio().getText();
        String producto = vista.getTxtProducto().getText();
        String cantidad = vista.getTxtCantidad().getText();
        String precioIVA = vista.getTxtPrecioConIVA().getText();
        String cliente = vista.getTxtCliente().getText();

        modelo.buscarTickets(numeroTicket, servicio, producto, cantidad, precioIVA, cliente, vista.getTablaTicket(), vista.getModeloTabla());
    }
}
