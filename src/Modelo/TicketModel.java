package Modelo;
import Database.ServiciosRealizados.Consulta;
import Database.ServiciosRealizados.Insertar;
import Objeto.Cliente;
import Objeto.Servicio;
import Objeto.Ticket;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class TicketModel {

    public void cargarTickets(JTable tablaTickets, DefaultTableModel modeloTabla) {
        Consulta consulta = new Consulta();
        consulta.consultarTicket();

        if (tablaTickets == null || tablaTickets.getModel() != modeloTabla){
            tablaTickets.setModel(modeloTabla);
        }

        tablaTickets.setEnabled(false);
        tablaTickets.setDefaultEditor(Object.class, new DefaultCellEditor(new JTextField()));

        modeloTabla.setRowCount(0);

        List<Ticket> ticketsList = consulta.getTicketList();
        for (Ticket ticket : ticketsList) {
            modeloTabla.addRow(new Object[]{ticket.getNumeroTicket()
                    , ticket.getServicio()
                    , ticket.getProducto()
                    , ticket.getCantidad()
                    , ticket.getPrecioConIVA()
                    , ticket.getCliente()
                    , ticket.getMetodoPago()
                    });
        }
    }

    public void guardarTicket(int numeroTicket, String servicio , String producto , int cantidad , double precioIVA , String cliente , String metodoPago) {
        Insertar insertar = new Insertar(numeroTicket, servicio , producto , cantidad , precioIVA , cliente , metodoPago);
        insertar.insertarTicket();
    }

    public void buscarTicket(int numeroTicketBuscar, JTable tablaTickets, DefaultTableModel modeloTabla){
        Consulta consulta = new Consulta();
        consulta.consultarTicketsPorID(numeroTicketBuscar);

        if (tablaTickets == null || tablaTickets.getModel() != modeloTabla){
            tablaTickets.setModel(modeloTabla);
        }

        tablaTickets.setEnabled(true);
        tablaTickets.setDefaultEditor(Object.class, new DefaultCellEditor(new JTextField()));
        modeloTabla.setRowCount(0);

        List<Ticket> ticketsList = consulta.getTicketList();
        for (Ticket ticket : ticketsList) {
            modeloTabla.addRow(new Object[]{ticket.getNumeroTicket()
                    , ticket.getServicio()
                    , ticket.getProducto()
                    , ticket.getCantidad()
                    , ticket.getPrecioConIVA()
                    , ticket.getCliente()
                    , ticket.getMetodoPago()
            });
        }
    }
/*
    public void actualizarServicio(List<Servicio> serviciosActualizados, List<Servicio> serviciosViejos) {
        int size = Math.min(serviciosActualizados.size(), serviciosViejos.size());

        for (int i = 0; i < size; i++) {
            Modificar modificar = new Modificar(serviciosActualizados.get(i).getNombre(), serviciosActualizados.get(i).getPrecio(), serviciosViejos.get(i).getNombre());
            modificar.modificarServicio();
        }
    }

    public void eliminarServicio(String nombreSeleccionado) {
        Eliminar eliminar = new Eliminar(nombreSeleccionado);
        eliminar.eliminarServicio();
    }*/
}
