package Controlador.Cliente;

import Modelo.ClientesModel;
import Objeto.Cliente;
import Vista.Cliente.ModificarClienteView;

import javax.swing.event.TableModelEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ModificarClienteController {
    private final ModificarClienteView vista;
    private final ClientesModel modelo;
    private final List<Cliente> clientesViejos;
    private final List<Cliente> clientesActualizados;

    public ModificarClienteController(ModificarClienteView vista, ClientesModel modelo) {
        this.vista = vista;
        this.modelo = modelo;
        this.clientesViejos = new ArrayList<>();
        this.clientesActualizados = new ArrayList<>();

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
                    modelo.buscarCliente(nombreBusqueda, vista.getTablaPrecios(), vista.getModeloTabla());
                    guardarServicio(clientesViejos);
                    escucharCambios();
                    modificarServicio();
                }
            });
        } catch (Exception ex) {
            vista.mostrarMensaje("Error al cargar los clientes de busqueda.");
        }
    }

    private void escucharCambios(){
        vista.getModeloTabla().addTableModelListener(e -> {
            if (e.getType() == TableModelEvent.UPDATE) {
                guardarServicio(clientesActualizados);
            }
        });
    }

    private void modificarServicio() {
        vista.modificarListener(e -> {
            modelo.actualizarCliente(new ArrayList<>(clientesActualizados), new ArrayList<>(clientesViejos));
            vista.mostrarMensaje("Servicio modificado exitosamente.");
        });
    }

    private void guardarServicio(List<Cliente> clientes) {
        clientes.clear();
        int rowCount = vista.getModeloTabla().getRowCount();
        for (int i = 0; i < rowCount; i++) {
            String nombre = (String) vista.getModeloTabla().getValueAt(i, 0);
            String apellido = (String) vista.getModeloTabla().getValueAt(i, 1);

            Object fechaObj = vista.getModeloTabla().getValueAt(i, 2);
            String fechaTexto = (fechaObj instanceof Date) ? new SimpleDateFormat("dd/MM/yyyy").format((Date) fechaObj) : (String) fechaObj;

            Date fecha = null;
            try {
                fecha = getFechaNacimiento(fechaTexto);
            } catch (ParseException e) {
                vista.mostrarMensaje("Fecha incorrecta");
                throw new RuntimeException(e);
            }

            java.sql.Date sqlFecha = new java.sql.Date(fecha.getTime());

            String telefono = (String) vista.getModeloTabla().getValueAt(i, 3);
            String email = (String) vista.getModeloTabla().getValueAt(i, 4);
            String cp = (String) vista.getModeloTabla().getValueAt(i, 5);

            clientes.add(new Cliente(nombre, apellido, sqlFecha, telefono, email, cp));
        }
    }

    public Date getFechaNacimiento(String fechaTexto) throws ParseException {
        List<SimpleDateFormat> dateFormats = new ArrayList<>();
        dateFormats.add(new SimpleDateFormat("dd/MM/yyyy"));
        dateFormats.add(new SimpleDateFormat("yyyy-MM-dd"));
        dateFormats.add(new SimpleDateFormat("MM/dd/yyyy"));

        for (SimpleDateFormat dateFormat : dateFormats) {
            try {
                dateFormat.setLenient(false);
                return dateFormat.parse(fechaTexto);
            } catch (ParseException e) {
                //
            }
        }
        throw new ParseException("Unparseable date: " + fechaTexto, 0);
    }

}