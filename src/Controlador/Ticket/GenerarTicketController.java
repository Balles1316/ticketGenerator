package Controlador.Ticket;

import Database.Servicios.Consulta;
import Modelo.ServiciosModel;
import Modelo.TicketModel;
import Objeto.Servicio;
import Objeto.Ticket;
import Vista.Ticket.GenerarTicketView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GenerarTicketController {
    private final GenerarTicketView vista;
    private final TicketModel modelo;
    private final ServiciosModel modeloServicios;
    public static int nTicket = 0;
    private List<Ticket> almacen;

    public GenerarTicketController(GenerarTicketView vista, TicketModel modelo) {
        this.vista = vista;
        this.modelo = modelo;
        this.almacen = new ArrayList<>();
        this.modeloServicios = new ServiciosModel();

        Ticket ultimoTicket = modelo.getLastTicket();
        if (ultimoTicket != null) {
            // Aquí puedes proceder con el ticket obtenido
            nTicket = ultimoTicket.getNumeroTicket() + 1; // Incrementar el número de ticket
            vista.setTxtNumeroTicket(String.valueOf(nTicket));
        } else {
            // Manejo del caso cuando no hay tickets en la base de datos
            nTicket = 1; // Puedes iniciar desde el número 1 si no hay tickets previos
            vista.setTxtNumeroTicket(String.valueOf(nTicket));
        }

        actualizarComboBoxCodigoServicio();
        establecerPrecios();
        guardarTicket();
    }

    public void actualizarComboBoxCodigoServicio() {
        List<Servicio> servicioList = modeloServicios.getServices();

        // Verificar si la lista de servicios es null
        if (servicioList == null) {
            // Manejo de caso cuando la lista de servicios es nula
            vista.mostrarMensaje("Lista Servicios es nula ! Introduce Servicios !");
            return; // Salir del método o realizar alguna acción de manejo
        }

        vista.getComboServicios().removeAllItems();

        for (Servicio servicio : servicioList) {
            vista.getComboServicios().addItem(servicio.getNombre());
        }
    }


    private void establecerPrecios() {
        vista.guardarListenerJComboBox(e -> {
            List<Servicio> servicioList = modeloServicios.getServices();

            String nombreSeleccionado = (String) vista.getComboServicios().getSelectedItem();
            if (nombreSeleccionado != null) {
                for (Servicio servicio : servicioList) {
                    if (servicio.getNombre().equals(nombreSeleccionado)) {
                        vista.getTxtPrecioConIVA().setText(String.valueOf(servicio.getPrecio()));

                        // Verificar si los campos no están vacíos antes de convertirlos
                        String numeroTicketStr = vista.getNumeroTicket();
                        String producto = vista.getProducto();
                        String cantidadStr = vista.getCantidad();
                        String precioConIVAStr = vista.getTxtPrecioConIVA().getText();
                        String cliente = vista.getClienteEncontrado();
                        String metodoPago = vista.getMetodoPago();

                        if (!numeroTicketStr.isEmpty() && !producto.isEmpty() && !cantidadStr.isEmpty() && !precioConIVAStr.isEmpty() && !cliente.isEmpty() && !metodoPago.isEmpty()) {
                            try {
                                int numeroTicket = Integer.parseInt(numeroTicketStr);
                                int cantidad = Integer.parseInt(cantidadStr);
                                double precioConIVA = Double.parseDouble(precioConIVAStr);

                                // Llamar al método para guardar en el almacén
                                guardarEnAlmacen(numeroTicket, nombreSeleccionado, producto, cantidad, precioConIVA, cliente, metodoPago);

                                // Actualizar el total acumulado en el campo de precio con IVA
                                double valance = almacen.stream().mapToDouble(Ticket::getPrecioConIVA).sum();
                                vista.getTxtPrecioConIVA().setText(String.valueOf(valance));

                            } catch (NumberFormatException ex) {
                                vista.mostrarMensaje("Formato incorrecto en número de ticket, cantidad o precio con IVA.");
                            }
                        } else {
                            vista.mostrarMensaje("Por favor, complete todos los campos.");
                        }
                        break;
                    }
                }
            }
        });
    }

    private void guardarEnAlmacen(int numeroTicket, String nombreSeleccionado, String producto, int cantidad, double precioConIVA, String cliente, String metodoPago) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String fecha = dateFormat.format(new Date());

        // Agregar ticket al almacen cuando se selecciona un servicio
        Ticket ticket = new Ticket(numeroTicket, nombreSeleccionado, producto, cantidad, precioConIVA, cliente, metodoPago, fecha);
        almacen.add(ticket);
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

                                            // Guardar Ticket en la BD
                                            modelo.guardarTicket(numeroTicket, servicio, producto, cantidad, precioConIVA, cliente, metodoPago, fecha);
                                            vista.mostrarMensaje("Ticket guardado correctamente.");

                                            // Traigo el nTicket, le sumo 1 y lo vuelvo a pasar
                                            nTicket = Integer.parseInt(vista.getNumeroTicket());
                                            nTicket++;
                                            vista.setTxtNumeroTicket(String.valueOf(nTicket));

                                            // Imprimir el ticket
                                            iniciarImpresion();
                                            vista.limpiarCampos();
                                            almacen.clear();
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
        job.setPrintable(new Printable() {
            @Override
            public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
                if (pageIndex > 0) {
                    return NO_SUCH_PAGE;
                }

                Graphics2D g2d = (Graphics2D) graphics;
                g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());

                int y = 20;
                int yShift = 10;
                int headerRectHeight = 15;

                g2d.setFont(new Font("Monospaced", Font.PLAIN, 9));

                ImageIcon icon = new ImageIcon("C:\\PARABEUS.jpg");
                // g2d.drawImage(icon.getImage(), 50, 20, 90, 30, rootPane);
                y += yShift + 30;
                g2d.drawString("-------------------------------------", 10, y);
                y += yShift;
                g2d.drawString("         Parabeus S.L        ", 10, y);
                y += yShift;

                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                String fecha = dateFormat.format(new Date());
                g2d.drawString("       Fecha/Hora: " + fecha, 0, y);
                y += yShift;
                g2d.drawString("       Calle Sagasta n 15    ", 10, y);
                y += yShift;
                g2d.drawString("       +34 915 21 48 86      ", 10, y);
                y += yShift;
                g2d.drawString("       nTicket : "  + (nTicket - 1), 10, y);
                y += yShift;
                g2d.drawString("-------------------------------------", 10, y);
                y += headerRectHeight;

                g2d.drawString(" Item Name                  Price   ", 10, y);
                y += yShift;
                g2d.drawString("-------------------------------------", 10, y);
                y += headerRectHeight;

                for (Ticket ticket : almacen) {
                    g2d.drawString(" " + ticket.getServicio() + "                            ", 10, y);
                    y += yShift;
                    g2d.drawString("      " + ticket.getCantidad() + " * " + ticket.getPrecioConIVA(), 10, y);
                    g2d.drawString(String.valueOf(ticket.getCantidad() * ticket.getPrecioConIVA()), 160, y);
                    y += yShift;
                }

                g2d.drawString("-------------------------------------", 10, y);
                y += yShift;
                double totalAmount = almacen.stream().mapToDouble(t -> t.getCantidad() * t.getPrecioConIVA()).sum();
                g2d.drawString(" Total amount:               " + totalAmount + "   ", 10, y);
                y += yShift;
                g2d.drawString("-------------------------------------", 10, y);
                y += yShift;

                g2d.drawString("*************************************", 10, y);
                y += yShift;
                g2d.drawString("       THANK YOU COME AGAIN          ", 10, y);
                y += yShift;
                g2d.drawString("*************************************", 10, y);
                y += yShift;
                g2d.drawString("       SOFTWARE BY:Balles1316          ", 10, y);
                y += yShift;
                g2d.drawString("    CONTACT: Github : Balles1316 ", 10, y);
                y += yShift;

                return PAGE_EXISTS;
            }
        });
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
