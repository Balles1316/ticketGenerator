package Modelo;
import Database.Servicios.Consulta;
import Database.Servicios.Eliminar;
import Database.Servicios.Insertar;
import Database.Servicios.Modificar;
import Objeto.Servicio;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class ServiciosModel {

    public void cargarPrecios(JTable tablaPrecios, DefaultTableModel modeloTabla) {
        Consulta consulta = new Consulta();
        consulta.consultarServicios();

        if (tablaPrecios == null || tablaPrecios.getModel() != modeloTabla){
            tablaPrecios.setModel(modeloTabla);
        }

        tablaPrecios.setEnabled(false);
        tablaPrecios.setDefaultEditor(Object.class, new DefaultCellEditor(new JTextField()));

        modeloTabla.setRowCount(0);

        List<Servicio> serviciosList = consulta.getServiciosList();
        for (Servicio servicio : serviciosList) {
            modeloTabla.addRow(new Object[]{servicio.getNombre(), servicio.getPrecio()});
        }
    }

    public void guardarPrecios(Double precio, String nombre) {
        Insertar insertar = new Insertar(nombre, precio);
        insertar.insertarServicio();
    }

    public void buscarServicio(String nombreBusqueda, JTable tablaPrecios, DefaultTableModel modeloTabla){
        Consulta consulta = new Consulta();
        consulta.consultarServiciosPorNombre(nombreBusqueda);

        if (tablaPrecios == null || tablaPrecios.getModel() != modeloTabla){
            tablaPrecios.setModel(modeloTabla);
        }

        tablaPrecios.setEnabled(true);
        tablaPrecios.setDefaultEditor(Object.class, new DefaultCellEditor(new JTextField()));
        modeloTabla.setRowCount(0);

        List<Servicio> serviciosList = consulta.getServiciosList();
        for (Servicio servicio : serviciosList) {
            modeloTabla.addRow(new Object[]{servicio.getNombre(), servicio.getPrecio()});
        }
    }

    public void actualizarServicio(List<Servicio> serviciosActualizados, List<Servicio> serviciosViejos) {
        int size = Math.min(serviciosActualizados.size(), serviciosViejos.size());

        for (int i = 0; i < size; i++) {
            Modificar modificar = new Modificar(serviciosActualizados.get(i).getNombre(), serviciosActualizados.get(i).getPrecio(), serviciosViejos.get(i).getNombre());
            modificar.modificarServicio();
        }
    }

    public void eliminarServicio(String nombreSeleccionado) {
        Eliminar eliminar = new Eliminar(nombreSeleccionado);
        eliminar.eliminarServicio();
    }
}
