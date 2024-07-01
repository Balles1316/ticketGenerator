package Database.Clientes;

import Objeto.Cliente;
import Objeto.Servicio;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Consulta {
    private static List<Cliente> clientesList;

    public Consulta() {
        clientesList = new ArrayList<>();
    }

    public void consultarClientes() {
        Connection conn = null;
        Statement s = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            s = conn.createStatement();
            rs = s.executeQuery("SELECT * FROM CLIENTES");

            clientesList.clear();

            while (rs.next()) {
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                Date fechaNacimiento = rs.getDate("fNacimiento");
                String telefono = rs.getString("telefono");
                String correoElectronico = rs.getString("email");
                String codigoPostal = rs.getString("cp");

                Cliente cliente = new Cliente(nombre, apellido, fechaNacimiento, telefono, correoElectronico, codigoPostal);
                clientesList.add(cliente);
            }
        } catch (SQLException ex) {
            System.out.println("Error en SELECT de CLIENTES");
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

    public void consultarClientesPorNombre(String nombreABuscar) {
        Connection conn = null;
        Statement s = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            s = conn.createStatement();
            rs = s.executeQuery("SELECT * FROM CLIENTES WHERE NOMBRE LIKE '%"+nombreABuscar+"%'");

            clientesList.clear();

            while (rs.next()) {
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                Date fechaNacimiento = rs.getDate("fNacimiento");
                String telefono = rs.getString("telefono");
                String correoElectronico = rs.getString("email");
                String codigoPostal = rs.getString("cp");

                Cliente cliente = new Cliente(nombre, apellido, fechaNacimiento, telefono, correoElectronico, codigoPostal);
                clientesList.add(cliente);
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

    public List<Cliente> getClientesList() {
        return clientesList;
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
