package Database.ServiciosRealizados;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Eliminar {
    private Connection conn = null;
    private Statement s = null;

    public void eliminarServicio(String nombreS) {
        try {
            conn = getConnection();
            s = conn.createStatement();

            PreparedStatement pstmt = conn.prepareStatement("DELETE FROM SERVICIOS WHERE nombre = ?");
            pstmt.setString(1, nombreS);
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error en DELETE de SERVICIOS");
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
