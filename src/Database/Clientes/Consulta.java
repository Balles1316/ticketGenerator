package Database.Clientes;

import Modelo.Servicio;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Consulta {
    private static List<Servicio> serviciosList;

    public Consulta() {
        serviciosList = new ArrayList<>();
    }

    public void consultarServicios() {
        Connection conn = null;
        Statement s = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            s = conn.createStatement();
            rs = s.executeQuery("SELECT * FROM SERVICIOS");

            serviciosList.clear();

            while (rs.next()) {
                String nombre = rs.getString("nombre");
                double precio = rs.getDouble("precio");

                Servicio servicio = new Servicio(nombre, precio);
                serviciosList.add(servicio);
            }
        } catch (SQLException ex) {
            System.out.println("Error en SELECT de SERVICIOS");
        } finally{
            try {
                if (s != null) {
                    s.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(Consulta.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void consultarServiciosPorNombre(String nombreABuscar) {
        Connection conn = null;
        Statement s = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            s = conn.createStatement();
            rs = s.executeQuery("SELECT * FROM SERVICIOS WHERE NOMBRE LIKE '%"+nombreABuscar+"%'");

            serviciosList.clear();

            while (rs.next()) {
                String nombre = rs.getString("nombre");
                double precio = rs.getDouble("precio");

                Servicio servicio = new Servicio(nombre, precio);
                serviciosList.add(servicio);
            }
        } catch (SQLException ex) {
            System.out.println("Error en SELECT de SERVICIOS");
        } finally{
            try {
                if (s != null) {
                    s.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(Consulta.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public List<Servicio> getServiciosList() {
        return serviciosList;
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
