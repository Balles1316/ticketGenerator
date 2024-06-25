package Database.ServiciosRealizados;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Insertar {
    private static String nombreS;
    private static Double precioS;
    private Connection conn = null;
    private PreparedStatement pstmt = null;

    public Insertar(String nombre, Double precio) {
        this.nombreS = nombre;
        this.precioS = precio;
    }

    public void insertarServicio() {
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement("INSERT INTO SERVICIOS (nombre, precio) VALUES (?, ?)");
            pstmt.setString(1, this.nombreS);
            pstmt.setDouble(2, this.precioS);
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error en INSERT de SERVICIOS: " + ex.getMessage());
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
