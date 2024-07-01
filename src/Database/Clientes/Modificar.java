package Database.Clientes;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Modificar {
    private Connection conn = null;
    private Statement s = null;
    private static String nombreNuevo;
    private static String nombreViejo;
    private String apellido;
    private Date fNacimiento;
    private String telefono;
    private String email;
    private String cp;

    public Modificar(String nombreNuevo, String apellido, Date fNacimiento, String telefono, String email, String cp, String nombreViejo) {
        this.nombreNuevo = nombreNuevo;
        this.nombreViejo = nombreViejo;
        this.apellido = apellido;
        this.fNacimiento = fNacimiento;
        this.telefono = telefono;
        this.email = email;
        this.cp = cp;
    }

    public void modificarCliente() {
        try {
            conn = getConnection();
            s = conn.createStatement();

            PreparedStatement pstmt = conn.prepareStatement("UPDATE CLIENTES SET nombre = ?, apellido = ?, fNacimiento = ?, telefono = ?, email = ?, cp = ? WHERE nombre like ?");
            pstmt.setString(1, nombreNuevo);
            pstmt.setString(2, apellido);
            pstmt.setDate(3, fNacimiento);
            pstmt.setString(4, this.telefono);
            pstmt.setString(5, this.email);
            pstmt.setString(6, this.cp);
            pstmt.setString(7, "%" + nombreViejo + "%");

            pstmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error en UPDATE de CLIENTES");
        } finally{
            try {
                if (s != null) {
                    s.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(CrearTabla.class.getName()).log(Level.SEVERE, null, ex);
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
