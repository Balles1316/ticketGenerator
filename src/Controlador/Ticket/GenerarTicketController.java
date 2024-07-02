package Controlador.Ticket;

import Modelo.TicketModel;
import Vista.Ticket.GenerarTicketView;

public class GenerarTicketController {
    private final GenerarTicketView vista;
    private final TicketModel modelo;

    public GenerarTicketController(GenerarTicketView vista, TicketModel modelo) {
        this.vista = vista;
        this.modelo = modelo;
    }

   /* private void guardarTicket(){
        vista.guardarListener(e -> {
            String nombre = vista.getNombre();
            String precio = vista.getPrecio();

            if (!nombre.isEmpty()) {
                if (!precio.isEmpty()) {
                    try {
                        double precioDouble = Double.parseDouble(precio);
                        if (precioDouble > 0.0) {
                            modelo.guardarPrecios(precioDouble, nombre);
                            vista.mostrarMensaje("Servicio guardado correctamente.");
                            vista.limpiarCampos();
                        } else {
                            vista.mostrarMensaje("El precio debe ser mayor que cero.");
                        }
                    } catch (NumberFormatException ex) {
                        vista.mostrarMensaje("Formato de precio incorrecto.");
                    }
                } else {
                    vista.mostrarMensaje("El precio no puede ser vacío.");
                }
            }
            else {
                vista.mostrarMensaje("El nombre no puede ser vacío.");
            }
        });*/
}
