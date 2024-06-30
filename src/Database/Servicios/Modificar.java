package Database.Servicios;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Modificar {
    private Connection conn = null;
    private Statement s = null;
    private static String nombreNuevo;
    private static String nombreViejo;
    private static Double precio;

    public Modificar(String nombreNuevo, Double precio, String nombreViejo) {
        this.nombreNuevo = nombreNuevo;
        this.nombreViejo = nombreViejo;
        this.precio = precio;
    }

    public void modificarServicio() {
        try {
            conn = getConnection();
            s = conn.createStatement();

            PreparedStatement pstmt = conn.prepareStatement("UPDATE SERVICIOS SET nombre = ?, precio = ? WHERE nombre like ?");
            pstmt.setString(1, nombreNuevo);
            pstmt.setDouble(2, precio);
            pstmt.setString(3, "%" + nombreViejo + "%");

            pstmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error en UPDATE de SERVICIOS");
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
