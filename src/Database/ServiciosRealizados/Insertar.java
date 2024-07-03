package Database.ServiciosRealizados;

import java.sql.*;

public class Insertar {
    private static int numeroTicket;
    private static String servicio;
    private static String producto;
    private static int cantidad;
    private static double precioIVA;
    private static String cliente;
    private static String metodoPago;
    private static String fecha;
    private Connection conn = null;
    private PreparedStatement pstmt = null;

    public Insertar(int numeroTicket, String servicio, String producto, int cantidad, double precioIVA, String cliente, String metodoPago, String fecha) {
        this.numeroTicket = numeroTicket;
        this.servicio = servicio;
        this.producto = producto;
        this.cantidad = cantidad;
        this.precioIVA = precioIVA;
        this.cliente = cliente;
        this.metodoPago = metodoPago;
        this.fecha = fecha;
    }

    public void insertarTicket() {
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement("INSERT INTO SERVICIOSREALIZADOS (numeroTicket, servicio, producto, cantidad, precioIVA, cliente, metodoPago,fecha) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
            pstmt.setInt(1, this.numeroTicket);
            pstmt.setString(2, this.servicio);
            pstmt.setString(3, this.producto);
            pstmt.setInt(4, this.cantidad);
            pstmt.setDouble(5, this.precioIVA);
            pstmt.setString(6, this.cliente);
            pstmt.setString(7, this.metodoPago);
            pstmt.setString(8, this.fecha);
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error en INSERT de SERVICIOSREALIZADOS: " + ex.getMessage());
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println("Error al cerrar conexión: " + ex.getMessage());
            }
        }
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
