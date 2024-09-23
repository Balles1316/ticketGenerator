package Controlador.Ticket;

import Modelo.ServiciosModel;
import Modelo.TicketModel;
import Objeto.Servicio;
import Objeto.Ticket;
import Vista.Ticket.GenerarTicketView;

import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.awt.print.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GenerarTicketController {
    private final GenerarTicketView vista;
    private final TicketModel modelo;
    private final ServiciosModel modeloServicios;
    public static int nTicket = 0;
    private final List<Ticket> almacen;

    Double bHeight=0.0;

    public GenerarTicketController(GenerarTicketView vista, TicketModel modelo) {
        this.vista = vista;
        this.modelo = modelo;
        this.almacen = new ArrayList<>();
        this.modeloServicios = new ServiciosModel();

        Ticket ultimoTicket = modelo.getLastTicket();
        if (ultimoTicket != null) {
            // Incrementar el número de ticket
            nTicket = ultimoTicket.getNumeroTicket() + 1;
            vista.setTxtNumeroTicket(String.valueOf(nTicket));
        } else {
            // Si no hay tickets previos, comenzar desde el número 1
            nTicket = 1;
            vista.setTxtNumeroTicket(String.valueOf(nTicket));
        }

        actualizarComboBoxCodigoServicio();
        establecerPrecios();
        guardarTicket();
    }

    public void actualizarComboBoxCodigoServicio() {
        List<Servicio> servicioList = modeloServicios.getServices();

        if (servicioList == null) {
            vista.mostrarMensaje("Lista Servicios es nula. ¡Introduce Servicios!");
            return;
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
                        vista.getTxtBalanceConIVA().setText(String.valueOf(servicio.getPrecio()));

                        String numeroTicketStr = vista.getNumeroTicket();
                        String producto = vista.getProducto();
                        String cantidadStr = vista.getCantidad();
                        String precioConIVAStr = vista.getTxtBalanceConIVA().getText();
                        String cliente = vista.getClienteEncontrado();
                        String metodoPago = vista.getMetodoPago();

                        if (!numeroTicketStr.isEmpty() && !producto.isEmpty() && !cantidadStr.isEmpty() && !precioConIVAStr.isEmpty() && !cliente.isEmpty() && !metodoPago.isEmpty()) {
                            try {
                                int numeroTicket = Integer.parseInt(numeroTicketStr);
                                int cantidad = Integer.parseInt(cantidadStr);
                                double precioConIVA = Double.parseDouble(precioConIVAStr);

                                guardarEnAlmacen(numeroTicket, nombreSeleccionado, producto, cantidad, precioConIVA, cliente, metodoPago);

                                // Actualizar el total acumulado
                                double valance = almacen.stream().mapToDouble(Ticket::getPrecioConIVA).sum();
                                vista.getTxtBalanceConIVA().setText(String.valueOf(valance));

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

        // Agregar ticket al almacen
        Ticket ticket = new Ticket(numeroTicket, nombreSeleccionado, producto, cantidad, precioConIVA, cliente, metodoPago, fecha);
        almacen.add(ticket);
    }

    private void guardarTicket() {
        vista.guardarListenerImprimir(e -> {
            String numeroTicketStr = vista.getNumeroTicket();
            String servicio = vista.getServicio();
            String producto = vista.getProducto();
            String cantidadStr = vista.getCantidad();
            String precioConIVAStr = String.valueOf(vista.getBalanceConIVA());
            String cliente = vista.getClienteEncontrado();
            String metodoPago = vista.getMetodoPago();

            if (!numeroTicketStr.isEmpty() && !producto.isEmpty() && !cantidadStr.isEmpty() && !precioConIVAStr.isEmpty() && !cliente.isEmpty() && !metodoPago.isEmpty()) {
                try {
                    int numeroTicket = Integer.parseInt(numeroTicketStr);
                    int cantidad = Integer.parseInt(cantidadStr);
                    double precioConIVA = Double.parseDouble(precioConIVAStr);

                    if (numeroTicket > 0 && cantidad > 0 && precioConIVA > 0.0) {

                        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                        String fecha = dateFormat.format(new Date());

                        modelo.guardarTicket(numeroTicket, servicio, producto, cantidad, precioConIVA, cliente, metodoPago, fecha);
                        vista.mostrarMensaje("Ticket guardado correctamente en la base de datos local.");

                        nTicket = Integer.parseInt(vista.getNumeroTicket()) + 1;
                        vista.setTxtNumeroTicket(String.valueOf(nTicket));

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
                vista.mostrarMensaje("Por favor, complete todos los campos.");
            }
        });
    }

    private void iniciarImpresion() {

        bHeight = Double.valueOf(almacen.size());
        //JOptionPane.showMessageDialog(rootPane, bHeight);

        PrinterJob pj = PrinterJob.getPrinterJob();
        pj.setPrintable(new BillPrintable(),getPageFormat(pj));
        try {
            pj.print();

        }
        catch (PrinterException ex) {
            vista.mostrarMensaje("Error al imprimir: " + ex.getMessage());
        }
    }

    public PageFormat getPageFormat(PrinterJob pj)
    {

        PageFormat pf = pj.defaultPage();
        Paper paper = pf.getPaper();

        double bodyHeight = bHeight;
        double headerHeight = 5.0;
        double footerHeight = 5.0;
        double width = cm_to_pp(8);
        double height = cm_to_pp(headerHeight+bodyHeight+footerHeight);
        paper.setSize(width, height);
        paper.setImageableArea(0,10,width,height - cm_to_pp(1));

        pf.setOrientation(PageFormat.PORTRAIT);
        pf.setPaper(paper);

        return pf;
    }

    protected static double cm_to_pp(double cm)
    {
        return toPPI(cm * 0.393600787);
    }

    protected static double toPPI(double inch)
    {
        return inch * 72d;
    }

    public class BillPrintable implements Printable {

        public int print(Graphics graphics, PageFormat pageFormat,int pageIndex)
                throws PrinterException
        {

            int r= almacen.size();
            ImageIcon icon=new ImageIcon("C:\\PARABEUS.jpg");
            int result = NO_SUCH_PAGE;
            if (pageIndex == 0) {

                Graphics2D g2d = (Graphics2D) graphics;
                double width = pageFormat.getImageableWidth();
                g2d.translate((int) pageFormat.getImageableX(),(int) pageFormat.getImageableY());



                //  FontMetrics metrics=g2d.getFontMetrics(new Font("Arial",Font.BOLD,7));

                try{
                    int y=20;
                    int yShift = 10;
                    int headerRectHeight=15;
                    // int headerRectHeighta=40;


                    g2d.setFont(new Font("Monospaced",Font.PLAIN,9));
                    g2d.drawImage(icon.getImage(), 50, 20, 90, 30, vista.getRootPane());y+=yShift+30;
                    g2d.drawString("-------------------------------------",12,y);y+=yShift;
                    g2d.drawString("         Parabeus.es         ",12,y);y+=yShift;
                    g2d.drawString("       NIE : B-87426813         ",12,y);y+=yShift;
                    g2d.drawString("       Calle Sagasta n 15    ",12,y);y+=yShift;
                    g2d.drawString("       +34 915 21 48 86      ",12,y);y+=yShift;
                    g2d.drawString("-------------------------------------",12,y);y+=headerRectHeight;

                    g2d.drawString(" Item Name                  Price   ",10,y);y+=yShift;
                    g2d.drawString("-------------------------------------",10,y);y+=headerRectHeight;

                    // Recorrer los artículos y ajustarlos al centro
                    for (Ticket ticket : almacen) {
                        g2d.drawString(" " + ticket.getServicio() + "                            ", 10, y);
                        y += yShift;
                        g2d.drawString("      " + ticket.getCantidad() + " * " + ticket.getPrecioConIVA(), 10, y);
                        y += yShift;
                    }

                    g2d.drawString("-------------------------------------",10,y);y+=yShift;
                    g2d.drawString(" Total amount:               "+vista.getBalanceConIVA()+"   ",10,y);y+=yShift;
                    g2d.drawString("-------------------------------------",10,y);y+=yShift;
                    g2d.drawString(" Cash      :                 "/*+txtcash.getText()*/+"   ",10,y);y+=yShift;
                    g2d.drawString("-------------------------------------",10,y);y+=yShift;
                    g2d.drawString(" Balance   :                 "/*+txtbalance.getText()*/+"   ",10,y);y+=yShift;

                    g2d.drawString("*************************************",10,y);y+=yShift;
                    g2d.drawString("       THANK YOU COME AGAIN            ",10,y);y+=yShift;
                    g2d.drawString("*************************************",10,y);y+=yShift;
                    g2d.drawString("       SOFTWARE BY:PARABEUS          ",10,y);y+=yShift;
                    g2d.drawString(" CONTACT: parabeuspeluqueria@gmail.com ",10,y);y+=yShift;


                }
                catch(Exception e){
                    e.printStackTrace();
                }

                result = PAGE_EXISTS;
            }
            return result;
        }
    }

}
