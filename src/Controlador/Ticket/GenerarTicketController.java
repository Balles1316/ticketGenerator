package Controlador.Ticket;

import Database.Servicios.Consulta;
import Modelo.TicketModel;
import Objeto.Servicio;
import Vista.Ticket.GenerarTicketView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class GenerarTicketController {
    private final GenerarTicketView vista;
    private final TicketModel modelo;
    public static int nTicket;

    public GenerarTicketController(GenerarTicketView vista, TicketModel modelo) {
        this.vista = vista;
        this.modelo = modelo;

        actualizarComboBoxCodigoServicio();
        establecerPrecios();
        guardarTicket();
    }

    public void actualizarComboBoxCodigoServicio() {
        Consulta consulta = new Consulta();
        consulta.consultarServicios();
        List<Servicio> servicioList = consulta.getServiciosList();

        vista.getComboServicios().removeAllItems();

        for (Servicio codigoServicio : servicioList) {
            vista.getComboServicios().addItem(codigoServicio.getNombre());
        }
    }

    private void establecerPrecios() {
        vista.guardarListenerJComboBox(e -> {
            Consulta consulta = new Consulta();
            consulta.consultarServicios();
            List<Servicio> servicioList = consulta.getServiciosList();

            String nombreSeleccionado = (String) vista.getComboServicios().getSelectedItem();
            if (nombreSeleccionado != null) {
                for (Servicio servicio : servicioList) {
                    if (servicio.getNombre().equals(nombreSeleccionado)) {
                        vista.getTxtPrecioConIVA().setText(String.valueOf(servicio.getPrecio()));
                        break;
                    }
                }
            }
        });
    }

    private void guardarTicket() {
        vista.guardarListenerImprimir(e -> {
            String numeroTicketStr = vista.getNumeroTicket();
            String servicio = vista.getServicio();
            String producto = vista.getProducto();
            String cantidadStr = vista.getCantidad();
            String precioConIVAStr = String.valueOf(vista.getPrecioConIVA());
            String cliente = vista.getClienteEncontrado();
            String metodoPago = vista.getMetodoPago();

            if (!numeroTicketStr.isEmpty()) {
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

                                            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                                            String fecha = dateFormat.format(new Date());

                                            //Guardo Ticket en la BD
                                            modelo.guardarTicket(numeroTicket, servicio, producto, cantidad, precioConIVA, cliente, metodoPago,fecha);
                                            vista.mostrarMensaje("Ticket guardado correctamente.");

                                            //Traigo el nTicket le sumo 1 y lo vuelvo a pasar
                                            nTicket = Integer.parseInt(vista.getNumeroTicket());
                                            nTicket ++ ;
                                            vista.setTxtNumeroTicket(String.valueOf(nTicket));

                                            //Lo imprimo
                                            iniciarImpresion();
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
            } else {
                vista.mostrarMensaje("El número de ticket no puede ser vacío.");
            }
        });
    }

    private void iniciarImpresion() {
        PrinterJob job = PrinterJob.getPrinterJob();
        job.setPrintable(modelo);
        boolean doPrint = job.printDialog();
        if (doPrint) {
            try {
                job.print();
            } catch (PrinterException e) {
                e.printStackTrace();
            }
        }
    }
}
