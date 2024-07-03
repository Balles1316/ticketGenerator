package Modelo;

import Database.ServiciosRealizados.Consulta;
import Database.ServiciosRealizados.Insertar;
import Objeto.Servicio;
import Objeto.Ticket;
import Vista.Ticket.GenerarTicketView;
import java.text.SimpleDateFormat;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.util.Date;
import java.util.List;

public class TicketModel implements Printable {

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
                    ticket.getMetodoPago(),
                    ticket.getFecha()
            });
        }
    }

    public void guardarTicket(int numeroTicket, String servicio, String producto, int cantidad, double precioIVA, String cliente, String metodoPago, String fechaHora) {
        Insertar insertar = new Insertar(numeroTicket, servicio, producto, cantidad, precioIVA, cliente, metodoPago , fechaHora);
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

    @Override
    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {

        Consulta consulta = new Consulta();
        consulta.consultarTicket();
        List<Ticket> ticketList = consulta.getTicketList();

        Ticket ticket = ticketList.getLast();

        if (pageIndex > 0) {
            return NO_SUCH_PAGE;
        }

        Graphics2D g2d = (Graphics2D) graphics;
        g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());

        int y = 20;
        int yShift = 10;
        int headerRectHeight = 15;

        g2d.setFont(new Font("Monospaced", Font.PLAIN, 9));

        // Assuming rootPane is correctly initialized, if not you may need to update this
        ImageIcon icon = new ImageIcon("C:\\PARABEUS.jpg");
        // g2d.drawImage(icon.getImage(), 50, 20, 90, 30, rootPane);
        y += yShift + 30;
        g2d.drawString("-------------------------------------", 10, y);
        y += yShift;
        g2d.drawString("         Parabeus S.L        ", 10, y);
        y += yShift;
        g2d.drawString("      Ticket number " + ticket.getNumeroTicket(), 10, y);
        y += yShift;
        // Formatear la fecha y hora
        g2d.drawString("       Fecha/Hora: " + ticket.getFecha(), 0, y);
        y += yShift;
        g2d.drawString("       Calle Sagasta n 15    ", 10, y);
        y += yShift;
        g2d.drawString("       +34 915 21 48 86      ", 10, y);
        y += yShift;
        g2d.drawString("-------------------------------------", 10, y);
        y += headerRectHeight;

        g2d.drawString(" Item Name                  Price   ", 10, y);
        y += yShift;
        g2d.drawString("-------------------------------------", 10, y);
        y += headerRectHeight;

        /*for (int s = 0; s < tickets.size(); s++) {
             g2d.drawString(" " + itemName.get(s) + "                            ", 10, y);
             y += yShift;
             g2d.drawString("      " + quantity.get(s) + " * " + itemPrice.get(s), 10, y);
             g2d.drawString(subtotal.get(s), 160, y);
             y += yShift;
         }*/

        g2d.drawString("-------------------------------------", 10, y);
        y += yShift;
        g2d.drawString(" Total amount:               " + /*txttotalAmount.getText() +*/ "   ", 10, y);
        y += yShift;
        g2d.drawString("-------------------------------------", 10, y);
        y += yShift;
        g2d.drawString(" Cash      :                 " + /*txtcash.getText() +*/ "   ", 10, y);
        y += yShift;
        g2d.drawString("-------------------------------------", 10, y);
        y += yShift;
        g2d.drawString(" Balance   :                 " + /*balance + */"   ", 10, y);
        y += yShift;
        g2d.drawString("-------------------------------------", 10, y);
        y += yShift;
        g2d.drawString(" Metodo Pago :              " + ticket.getMetodoPago() +"   ", 10, y);
        y += yShift;

        g2d.drawString("*************************************", 10, y);
        y += yShift;
        g2d.drawString("       THANK YOU COME AGAIN          ", 10, y);
        y += yShift;
        g2d.drawString("*************************************", 10, y);
        y += yShift;
        g2d.drawString("       SOFTWARE BY:PARABEUS          ", 10, y);
        y += yShift;
        g2d.drawString("CONTACT: parabeuspeluqueria@gmail.com", 10, y);
        y += yShift;

        return PAGE_EXISTS;
    }
}
