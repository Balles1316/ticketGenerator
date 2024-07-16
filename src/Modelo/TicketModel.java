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
                    ticket.getPrecioConIVA(),
                    ticket.getCliente(),
                    ticket.getMetodoPago(),
                    ticket.getFecha()
            });
        }
    }

    public void guardarTicket(int numeroTicket, String servicio, String producto, int cantidad, double precioIVA, String cliente, String metodoPago, String fechaHora) {
        Insertar insertar = new Insertar(numeroTicket, servicio, producto, cantidad, precioIVA, cliente, metodoPago, fechaHora);
        insertar.insertarTicket();
    }

    public void buscarTickets(String numeroTicket, String servicio, String producto, String precioIVA, String cliente, String metodoPago, String fecha, JTable tablaTickets, DefaultTableModel modeloTabla) {
        Consulta consulta = new Consulta();
        consulta.consultarTicketsConFiltros(numeroTicket, servicio, producto, precioIVA, cliente, metodoPago, fecha);

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
                    ticket.getPrecioConIVA(),
                    ticket.getCliente(),
                    ticket.getMetodoPago(),
                    ticket.getFecha()
            });
        }
    }

    public Ticket getLastTicket() {
        Consulta consulta = new Consulta();
        consulta.consultarTicket();
        List<Ticket> ticketList = consulta.getTicketList();

        if (!ticketList.isEmpty()) {
            return ticketList.get(ticketList.size() - 1); // Devuelve el último ticket
        } else {
            // Manejo de caso cuando no hay tickets en la lista
            return null; // O puedes lanzar una excepción, dependiendo de tus necesidades
        }
    }
}
