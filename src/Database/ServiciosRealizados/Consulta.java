package Database.ServiciosRealizados;

import Objeto.Ticket;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Consulta {
    private static List<Ticket> ticketsList;

    public Consulta() {
        ticketsList = new ArrayList<>();
    }

    public void consultarTicket() {
        Connection connection = null;
        Statement s = null;
        ResultSet rs = null;

        try {
            connection = getConnection();
            s = connection.createStatement();
            rs = s.executeQuery("SELECT * FROM SERVICIOSREALIZADOS");

            ticketsList.clear();

            while (rs.next()) {
                int numeroTicket = rs.getInt("numeroTicket");
                String servicio = rs.getString("servicio");
                int cantidad = rs.getInt("cantidad");
                double precioIVA = rs.getDouble("precioIVA");
                String cliente = rs.getString("cliente");
                String metodoPago = rs.getString("metodoPago");

                Ticket ticket = new Ticket(numeroTicket, servicio,cantidad,precioIVA,cliente,metodoPago);
                ticketsList.add(ticket);
            }
        } catch (SQLException ex) {
            System.out.println("Error en SELECT de SERVICIOSREALIZADOS");
        } finally{
            try {
                if (s != null) {
                    s.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(Consulta.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void consultarTicketsPorID(int numeroTicketBuscar) {
        Connection connection = null;
        Statement s = null;
        ResultSet rs = null;

        try {
            connection = getConnection();
            s = connection.createStatement();
            rs = s.executeQuery("SELECT * FROM SERVICIOSREALIZADOS WHERE NOMBRE LIKE '%"+numeroTicketBuscar+"%'");

            ticketsList.clear();

            while (rs.next()) {
                int numeroTicket = rs.getInt("numeroTicket");
                String servicio = rs.getString("servicio");
                int cantidad = rs.getInt("cantidad");
                double precioIVA = rs.getDouble("precioIVA");
                String cliente = rs.getString("cliente");
                String metodoPago = rs.getString("metodoPago");

                Ticket ticket = new Ticket(numeroTicket, servicio,cantidad,precioIVA,cliente,metodoPago);
                ticketsList.add(ticket);
            }
        } catch (SQLException ex) {
            System.out.println("Error en SELECT de SERVICIOS");
        } finally{
            try {
                if (s != null) {
                    s.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(Consulta.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public List<Ticket> getTicketList() {
        return ticketsList;
    }

    private static Connection getConnection() {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException ex) {
            System.out.println("No se puede obtener la clase Driver");
            return null;
        }

        try {
            return DriverManager.getConnection("jdbc:sqlite:PARABEUS");
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
            ex.printStackTrace();
            return null;
        }
    }
}
