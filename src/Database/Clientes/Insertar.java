package Database.Clientes;

import java.sql.*;

public class Insertar {

    private Connection conn = null;
    private PreparedStatement pstmt = null;

    private String nombre;
    private String apellido;
    private Date fNacimiento;
    private String telefono;
    private String email;
    private String cp;

    public Insertar(String nombre, String apellido, Date fNacimiento, String telefono, String email, String cp) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.fNacimiento = fNacimiento;
        this.telefono = telefono;
        this.email = email;
        this.cp = cp;
    }

    public void insertarCliente() {
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement("INSERT INTO CLIENTES VALUES (?, ?, ?, ?, ?, ?)");
            pstmt.setString(1, this.nombre);
            pstmt.setString(2, this.apellido);
            pstmt.setDate(3, fNacimiento);
            pstmt.setString(4, this.telefono);
            pstmt.setString(5, this.email);
            pstmt.setString(6, this.cp);
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
