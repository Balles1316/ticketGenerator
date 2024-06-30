package Controlador.Servicio;

import Modelo.ServiciosModel;
import Vista.Servicio.MostrarServiciosView;

public class MostrarServiciosController {
    private final MostrarServiciosView vista;
    private final ServiciosModel modelo;

    public MostrarServiciosController(MostrarServiciosView vista, ServiciosModel modelo) {
        this.vista = vista;
        this.modelo = modelo;

        try{
            cargarPrecios();
        } catch (Exception ex) {
            vista.mostrarMensaje("Error al cargar los precios.");
        }
    }

    private void cargarPrecios() {
        modelo.cargarPrecios(vista.getTablaPrecios(), vista.getModeloTabla());
    }
}
