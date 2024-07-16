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
                String producto = rs.getString("producto");
                int cantidad = rs.getInt("cantidad");
                double precioIVA = rs.getDouble("precioIVA");
                String cliente = rs.getString("cliente");
                String metodoPago = rs.getString("metodoPago");
                String fechaHora = rs.getString("fecha");

                Ticket ticket = new Ticket(numeroTicket, servicio, producto, cantidad, precioIVA, cliente, metodoPago, fechaHora);
                ticketsList.add(ticket);
            }
        } catch (SQLException ex) {
            System.out.println("Error en SELECT de SERVICIOSREALIZADOS");
        } finally {
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

    public void consultarTicketsConFiltros(String numeroTicket, String servicio, String producto, String precioIVA, String cliente, String metodoPago, String fecha) {
        Connection connection = null;
        Statement s = null;
        ResultSet rs = null;

        try {
            connection = getConnection();
            s = connection.createStatement();
            String query = "SELECT * FROM SERVICIOSREALIZADOS WHERE 1=1";

            if (!numeroTicket.isEmpty()) {
                query += " AND numeroTicket LIKE '%" + numeroTicket + "%'";
            }
            if (!servicio.isEmpty()) {
                query += " AND servicio LIKE '%" + servicio + "%'";
            }
            if (!producto.isEmpty()) {
                query += " AND producto LIKE '%" + producto + "%'";
            }
            if (!precioIVA.isEmpty()) {
                query += " AND precioIVA LIKE '%" + precioIVA + "%'";
            }
            if (!cliente.isEmpty()) {
                query += " AND cliente LIKE '%" + cliente + "%'";
            }
            if (!metodoPago.isEmpty()) {
                query += " AND metodoPago LIKE '%" + metodoPago + "%'";
            }
            if (!fecha.isEmpty()) {
                query += " AND fecha LIKE '%" + fecha + "%'";
            }

            rs = s.executeQuery(query);

            ticketsList.clear();

            while (rs.next()) {
                int numeroTicketVal = rs.getInt("numeroTicket");
                String servicioVal = rs.getString("servicio");
                String productoVal = rs.getString("producto");
                int cantidadVal = rs.getInt("cantidad");
                double precioIVAVal = rs.getDouble("precioIVA");
                String clienteVal = rs.getString("cliente");
                String metodoPagoVal = rs.getString("metodoPago");
                String fechaHora = rs.getString("fecha");

                Ticket ticket = new Ticket(numeroTicketVal, servicioVal, productoVal, cantidadVal, precioIVAVal, clienteVal, metodoPagoVal, fechaHora);
                ticketsList.add(ticket);
            }
        } catch (SQLException ex) {
            System.out.println("Error en SELECT de SERVICIOS");
        } finally {
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
