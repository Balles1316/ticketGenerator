package Controlador.Cliente;

import Modelo.ClientesModel;
import Modelo.ServiciosModel;
import Vista.Cliente.EliminarClienteView;
import Vista.Servicio.EliminarServiciosView;

public class EliminarClienteController {
    private final EliminarClienteView vista;
    private final ClientesModel modelo;
    private String nombreSeleccionado;

    public EliminarClienteController(EliminarClienteView vista, ClientesModel modelo) {
        this.vista = vista;
        this.modelo = modelo;

        buscarServicio();
    }

    private void buscarServicio() {
        try {
            vista.buscarListener(e -> {
                String nombreBusqueda = vista.getNombreABuscar();
                if (nombreBusqueda.isEmpty()) {
                    vista.mostrarMensaje("Por favor, ingresa un nombre para buscar.");
                } else {
                    vista.getScrollPane().setVisible(true);
                    vista.getPanelDatos().setVisible(true);
                    vista.getBtnEliminar().setVisible(true);
                    vista.getBtnEliminar().setVisible(true);
                    modelo.buscarCliente(nombreBusqueda, vista.getTablaPrecios(), vista.getModeloTabla());
                    escucharTabla();
                    eliminarCliente();
                }
            });
        } catch (Exception ex) {
            vista.mostrarMensaje("Error al cargar los servicios de busqueda.");
        }
    }

    private void escucharTabla() {
        vista.getTablaPrecios().getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) { // Para evitar múltiples eventos por un mismo click
                int selectedRow = vista.getTablaPrecios().getSelectedRow();
                if (selectedRow != -1) { // Verificar que se ha seleccionado una fila
                    nombreSeleccionado = (String) vista.getTablaPrecios().getValueAt(selectedRow, 0);
                }
            }
        });
    }


    private void eliminarCliente() {
        vista.eliminarListener(e -> vista.confirmarEliminacion(nombreSeleccionado, () -> {
            modelo.eliminarCliente(nombreSeleccionado);
            vista.mostrarMensaje("Servicio eliminado.");
        }));
    }
}
