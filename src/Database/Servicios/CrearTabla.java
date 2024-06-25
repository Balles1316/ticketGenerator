package Database.Servicios;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CrearTabla {
    public static void main(String[] args) {
        Connection conn = null;
        Statement s = null;

        try {
            conn = getConnection();
            s = conn.createStatement();
            s.execute("CREATE TABLE IF NOT EXISTS SERVICIOS ("
                        + "nombre TEXT NOT NULL,"
                        + "precio REAL)"
            );
        } catch (SQLException ex) {
            System.out.println("Error en CREATE de SERVICIOS");
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
