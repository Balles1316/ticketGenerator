package Modelo;

import Database.Clientes.Consulta;
import Database.Clientes.Insertar;
import Database.Clientes.Modificar;
import Database.Clientes.Eliminar;
import Objeto.Cliente;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.Date;
import java.util.List;

public class ClientesModel {
    public void cargarClientes(JTable tablaPrecios, DefaultTableModel modeloTabla) {
        Consulta consulta = new Consulta();
        consulta.consultarClientes();

        if (tablaPrecios == null || tablaPrecios.getModel() != modeloTabla){
            tablaPrecios.setModel(modeloTabla);
        }

        tablaPrecios.setEnabled(true);
        tablaPrecios.setDefaultEditor(Object.class, new DefaultCellEditor(new JTextField()));

        modeloTabla.setRowCount(0);

        List<Cliente> clientesList = consulta.getClientesList();
        for (Cliente cliente : clientesList) {
            modeloTabla.addRow(new Object[]{cliente.getNombre(), cliente.getApellido(), cliente.getfNacimiento(), cliente.getTelefono(), cliente.getEmail(), cliente.getCp()});
        }
    }

    public void guardarCliente(String nombre, String apellido, Date fNacimiento, String telefono, String email, String cp) {
        Insertar insertar = new Insertar(nombre, apellido, fNacimiento, telefono, email, cp);
        insertar.insertarCliente();
    }

    public void buscarCliente(String nombreBusqueda, JTable tablaPrecios, DefaultTableModel modeloTabla){
        Database.Clientes.Consulta consulta = new Database.Clientes.Consulta();
        consulta.consultarClientesPorNombre(nombreBusqueda);

        if (tablaPrecios == null || tablaPrecios.getModel() != modeloTabla){
            tablaPrecios.setModel(modeloTabla);
        }

        tablaPrecios.setEnabled(true);
        tablaPrecios.setDefaultEditor(Object.class, new DefaultCellEditor(new JTextField()));
        modeloTabla.setRowCount(0);

        List<Cliente> clientesList = consulta.getClientesList();
        for (Cliente cliente : clientesList) {
            modeloTabla.addRow(new Object[]{cliente.getNombre(), cliente.getApellido(), cliente.getfNacimiento(), cliente.getTelefono(), cliente.getEmail(), cliente.getCp()});
        }
    }

    public void actualizarCliente(List<Cliente> clientesActualizados, List<Cliente> clientesViejos) {
        int size = Math.min(clientesActualizados.size(), clientesViejos.size());

        for (int i = 0; i < size; i++) {
            Modificar modificar = new Modificar(clientesActualizados.get(i).getNombre(), clientesActualizados.get(i).getApellido(), (Date) clientesActualizados.get(i).getfNacimiento(), clientesActualizados.get(i).getTelefono(), clientesActualizados.get(i).getEmail(), clientesActualizados.get(i).getCp(), clientesViejos.get(i).getNombre());
            modificar.modificarCliente();
        }
    }

    public void eliminarCliente(String nombreSeleccionado) {
        Eliminar eliminar = new Eliminar(nombreSeleccionado);
        eliminar.eliminarServicio();
    }
}
