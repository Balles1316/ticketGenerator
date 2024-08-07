package Database.ServiciosRealizados;

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
            s.execute("CREATE TABLE IF NOT EXISTS SERVICIOSREALIZADOS ("
                    + "numeroTicket INT NOT NULL,"
                    + "servicio TEXT NOT NULL,"
                    + "producto TEXT NOT NULL,"
                    + "cantidad INT NOT NULL,"
                    + "precioIVA REAL NOT NULL,"
                    + "cliente TEXT NOT NULL,"
                    + "metodoPago TEXT NOT NULL,"
                    + "fecha TEXT NOT NULL"
                    + ");");
        } catch (SQLException ex) {
            System.out.println("Error en CREATE de SERVICIOSREALIZADOS: " + ex.getMessage());
        } finally {
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
