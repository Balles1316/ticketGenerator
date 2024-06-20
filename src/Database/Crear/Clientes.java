package Database.Crear;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Clientes {
    public static void main(String[] args) {
        Connection conn = null;
        Statement s = null;

        try {
            conn = getConnection();
            s = conn.createStatement();
            s.execute("CREATE TABLE IF NOT EXISTS CLIENTES ("
                        + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                        + "nombre TEXT NOT NULL,"
                        + "descripcion TEXT,"
                        + "precio REAL)"
            );
        } catch (SQLException ex) {
            System.out.println("Error en CREATE de CLIENTES");
        } finally{
            try {
                if (s != null) {
                    s.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(Clientes.class.getName()).log(Level.SEVERE, null, ex);
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
