package Controlador.Servicio;

import Modelo.ServiciosModel;
import Objeto.Servicio;
import Vista.Servicio.ModificarServiciosView;

import javax.swing.event.TableModelEvent;
import java.util.ArrayList;
import java.util.List;

public class ModificarServiciosController {
    private final ModificarServiciosView vista;
    private final ServiciosModel modelo;
    private final List<Servicio> serviciosViejos;
    private final List<Servicio> serviciosActualizados;

    public ModificarServiciosController(ModificarServiciosView vista, ServiciosModel modelo) {
        this.vista = vista;
        this.modelo = modelo;
        this.serviciosViejos = new ArrayList<>();
        this.serviciosActualizados = new ArrayList<>();

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
                    vista.getBtnModificar().setVisible(true);
                    modelo.buscarServicio(nombreBusqueda, vista.getTablaPrecios(), vista.getModeloTabla());
                    guardarServicio(serviciosViejos);
                    escucharCambios();
                    modificarServicio();
                }
            });
        } catch (Exception ex) {
            vista.mostrarMensaje("Error al cargar los servicios de busqueda.");
        }
    }

    private void escucharCambios(){
        vista.getModeloTabla().addTableModelListener(e -> {
            if (e.getType() == TableModelEvent.UPDATE) {
                guardarServicio(serviciosActualizados);
            }
        });
    }

    private void modificarServicio() {
        vista.modificarListener(e -> {
            modelo.actualizarServicio(new ArrayList<>(serviciosActualizados), new ArrayList<>(serviciosViejos));
            vista.mostrarMensaje("Servicio modificado exitosamente.");
        });
    }

    private void guardarServicio(List<Servicio> servicios) {
        servicios.clear();
        int rowCount = vista.getModeloTabla().getRowCount();
        for (int i = 0; i < rowCount; i++) {
            String producto = (String) vista.getModeloTabla().getValueAt(i, 0);
            Object precioObj = vista.getModeloTabla().getValueAt(i, 1);

            double precio;
            if (precioObj instanceof Double) {
                precio = (Double) precioObj;
            } else if (precioObj instanceof String) {
                try {
                    precio = Double.parseDouble((String) precioObj);
                } catch (NumberFormatException e) {
                    vista.mostrarMensaje("Precio no válido para " + producto);
                    precio = 0.0;
                }
            } else {
                vista.mostrarMensaje("Tipo de dato no esperado para precio de " + producto);
                precio = 0.0;
            }

            servicios.add(new Servicio(producto, precio));
        }
    }

}