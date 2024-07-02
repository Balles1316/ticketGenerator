package Database.ServiciosRealizados;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Insertar {
    private static int numeroTicket;
    private static String servicio;
    private static int cantidad;
    private static double precioIVA;
    private static String cliente;
    private static String metodoPago;
    private Connection conn = null;
    private PreparedStatement pstmt = null;

    public Insertar(int numeroTicket, String servicio, int cantidad, double precioIVA, String cliente, String metodoPago) {
        this.numeroTicket = numeroTicket;
        this.servicio = servicio;
        this.cantidad = cantidad;
        this.precioIVA = precioIVA;
        this.cliente = cliente;
        this.metodoPago = metodoPago;
    }

    public void insertarTicket() {
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement("INSERT INTO SERVICIOSREALIZADOS (numeroTicket, servicio, cantidad, precioIVA, cliente, metodoPago) VALUES (?, ?, ?, ?, ?, ?)");
            pstmt.setInt(1, this.numeroTicket);
            pstmt.setString(2, this.servicio);
            pstmt.setInt(3, this.cantidad);
            pstmt.setDouble(4, this.precioIVA);
            pstmt.setString(5, this.cliente);
            pstmt.setString(6, this.metodoPago);
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
