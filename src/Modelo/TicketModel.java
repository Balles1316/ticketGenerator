package Modelo;

import Database.ServiciosRealizados.Consulta;
import Database.ServiciosRealizados.Insertar;
import Objeto.Ticket;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class TicketModel {

    public void cargarTickets(JTable tablaTickets, DefaultTableModel modeloTabla) {
        Consulta consulta = new Consulta();
        consulta.consultarTicket();

        if (tablaTickets == null || tablaTickets.getModel() != modeloTabla) {
            tablaTickets.setModel(modeloTabla);
        }

        tablaTickets.setEnabled(false);
        tablaTickets.setDefaultEditor(Object.class, new DefaultCellEditor(new JTextField()));

        modeloTabla.setRowCount(0);

        List<Ticket> ticketsList = consulta.getTicketList();
        for (Ticket ticket : ticketsList) {
            modeloTabla.addRow(new Object[]{
                    ticket.getNumeroTicket(),
                    ticket.getServicio(),
                    ticket.getProducto(),
                    ticket.getCantidad(),
                    ticket.getPrecioConIVA(),
                    ticket.getCliente(),
                    ticket.getMetodoPago()
            });
        }
    }

    public void guardarTicket(int numeroTicket, String servicio, String producto, int cantidad, double precioIVA, String cliente, String metodoPago) {
        Insertar insertar = new Insertar(numeroTicket, servicio, producto, cantidad, precioIVA, cliente, metodoPago);
        insertar.insertarTicket();
    }

    public void buscarTickets(String numeroTicket, String servicio, String producto, String cantidad, String precioIVA, String cliente, JTable tablaTickets, DefaultTableModel modeloTabla) {
        Consulta consulta = new Consulta();
        consulta.consultarTicketsConFiltros(numeroTicket, servicio, producto, cantidad, precioIVA, cliente);

        if (tablaTickets == null || tablaTickets.getModel() != modeloTabla) {
            tablaTickets.setModel(modeloTabla);
        }

        tablaTickets.setEnabled(true);
        tablaTickets.setDefaultEditor(Object.class, new DefaultCellEditor(new JTextField()));
        modeloTabla.setRowCount(0);

        List<Ticket> ticketsList = consulta.getTicketList();
        for (Ticket ticket : ticketsList) {
            modeloTabla.addRow(new Object[]{
                    ticket.getNumeroTicket(),
                    ticket.getServicio(),
                    ticket.getProducto(),
                    ticket.getCantidad(),
                    ticket.getPrecioConIVA(),
                    ticket.getCliente(),
                    ticket.getMetodoPago()
            });
        }
    }
}
