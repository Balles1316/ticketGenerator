package Controlador.Ticket;

import Modelo.TicketModel;
import Vista.Ticket.GenerarTicketView;

public class GenerarTicketController {
    private final GenerarTicketView vista;
    private final TicketModel modelo;

    public GenerarTicketController(GenerarTicketView vista, TicketModel modelo) {
        this.vista = vista;
        this.modelo = modelo;

        guardarTicket();
    }

    private void guardarTicket() {
        vista.guardarListener(e -> {
            String numeroTicketStr = vista.getNumeroTicket();
            String servicio = vista.getServicio();
            String producto = vista.getProducto();
            String cantidadStr = vista.getCantidad();
            String precioConIVAStr = String.valueOf(vista.getPrecioConIVA());
            String cliente = vista.getClienteEncontrado();
            String metodoPago = vista.getMetodoPago();

            if (!numeroTicketStr.isEmpty()) {
/*
                if (!servicio.isEmpty()) {
*/
                    if (!producto.isEmpty()) {
                        if (!cantidadStr.isEmpty()) {
                            if (!precioConIVAStr.isEmpty()) {
                                if (!cliente.isEmpty()) {
                                    if (!metodoPago.isEmpty()) {
                                        try {
                                            int numeroTicket = Integer.parseInt(numeroTicketStr);
                                            int cantidad = Integer.parseInt(cantidadStr);
                                            double precioConIVA = Double.parseDouble(precioConIVAStr);

                                            if (numeroTicket > 0 && cantidad > 0 && precioConIVA > 0.0) {
                                                modelo.guardarTicket(numeroTicket, servicio, producto, cantidad, precioConIVA, cliente, metodoPago);
                                                vista.mostrarMensaje("Ticket guardado correctamente.");
                                                vista.limpiarCampos();
                                            } else {
                                                vista.mostrarMensaje("El número de ticket, la cantidad y el precio con IVA deben ser mayores que cero.");
                                            }
                                        } catch (NumberFormatException ex) {
                                            vista.mostrarMensaje("Formato incorrecto en número de ticket, cantidad o precio con IVA.");
                                        }
                                    } else {
                                        vista.mostrarMensaje("El método de pago no puede ser vacío.");
                                    }
                                } else {
                                    vista.mostrarMensaje("El cliente no puede ser vacío.");
                                }
                            } else {
                                vista.mostrarMensaje("El precio con IVA no puede ser vacío.");
                            }
                        } else {
                            vista.mostrarMensaje("La cantidad no puede ser vacía.");
                        }
                    } else {
                        vista.mostrarMensaje("El producto no puede ser vacío.");
                    }
                /*} else {
                    vista.mostrarMensaje("El servicio no puede ser vacío.");
                }*/
            } else {
                vista.mostrarMensaje("El número de ticket no puede ser vacío.");
            }
        });
    }

}
