package Controlador.Cliente;

import Modelo.ClientesModel;
import Vista.Cliente.MostrarClienteView;

public class MostrarClienteController {
    private final MostrarClienteView vista;
    private final ClientesModel modelo;

    public MostrarClienteController(MostrarClienteView vista, ClientesModel modelo) {
        this.vista = vista;
        this.modelo = modelo;

        try{
            cargarClientes();
        } catch (Exception ex) {
            vista.mostrarMensaje("Error al cargar los clientes.");
        }
    }

    private void cargarClientes() {
        modelo.cargarClientes(vista.getTablaClientes(), vista.getModeloTabla());
    }
}
