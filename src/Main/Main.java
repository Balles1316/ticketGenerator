package Main;

import Controlador.Servicio.EliminarServiciosController;
import Controlador.Servicio.InsertarServiciosController;
import Controlador.Servicio.ModificarServiciosController;
import Controlador.Servicio.MostrarServiciosController;
import Controlador.Ticket.EliminarTicketController;
import Controlador.Ticket.GenerarTicketController;
import Controlador.Ticket.ModificarTicketController;
import Controlador.Ticket.MostrarTicketController;
import Modelo.ServiciosModel;
import Modelo.TicketModel;
import Vista.Servicio.EliminarServiciosView;
import Vista.Servicio.InsertarServiciosView;
import Vista.Servicio.ModificarServiciosView;
import Vista.Servicio.MostrarServiciosView;
import Vista.Ticket.EliminarTicketView;
import Vista.Ticket.GenerarTicketView;
import Vista.Ticket.ModificarTicketView;
import Vista.Ticket.MostrarTicketView;

import javax.swing.*;
import java.awt.event.ActionListener;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JMenuBar menuBar = new JMenuBar();

            JFrame frame = new JFrame("Parabeus Ticket Generator");
            frame.setJMenuBar(menuBar);
            frame.setSize(800, 600);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLocationRelativeTo(null);

            tickets(menuBar, frame);
            servicios(menuBar, frame);
            clients(menuBar, frame);

            // Initialize and show GenerarTicketView by default
            TicketModel model = new TicketModel();
            GenerarTicketView generarTicketView = new GenerarTicketView();
            new GenerarTicketController(generarTicketView, model);
            frame.add(generarTicketView);
            frame.setVisible(true);
        });
    }

    private static void mostrarVista(JFrame frame, JPanel nuevaVista) {
        frame.getContentPane().removeAll(); // LIMPIA CONTENIDO ANTERIOR
        frame.add(nuevaVista);
        frame.revalidate(); // REVALIDA
        frame.repaint(); // REPINTA
    }

    public static void servicios(JMenuBar menuBar, JFrame frame){
        ServiciosModel model = new ServiciosModel();

        JMenu menuServicios = new JMenu("Servicios");

        JMenuItem menuItemListarServicios = new JMenuItem("Listar servicios");
        JMenuItem menuItemInsertarServicios = new JMenuItem("Insertar servicios");
        JMenuItem menuItemModificarServicios = new JMenuItem("Modificar servicios");
        JMenuItem menuItemEliminarServicios = new JMenuItem("Eliminar servicios");

        menuServicios.add(menuItemListarServicios);
        menuServicios.add(menuItemInsertarServicios);
        menuServicios.add(menuItemModificarServicios);
        menuServicios.add(menuItemEliminarServicios);

        menuBar.add(menuServicios);

        ActionListener mostrarVistaAction = e -> {
            JMenuItem menuItem = (JMenuItem) e.getSource();
            JPanel nuevaVista = null;

            switch (menuItem.getText()) {
                case "Listar servicios":
                    nuevaVista = new MostrarServiciosView();
                    new MostrarServiciosController((MostrarServiciosView) nuevaVista, model);
                    break;
                case "Insertar servicios":
                    nuevaVista = new InsertarServiciosView();
                    new InsertarServiciosController((InsertarServiciosView) nuevaVista, model);
                    break;
                case "Modificar servicios":
                    nuevaVista = new ModificarServiciosView();
                    new ModificarServiciosController((ModificarServiciosView) nuevaVista, model);
                    break;
                case "Eliminar servicios":
                    nuevaVista = new EliminarServiciosView();
                    new EliminarServiciosController((EliminarServiciosView) nuevaVista, model);
                    break;
            }

            if (nuevaVista != null) {
                mostrarVista(frame, nuevaVista);
            }
        };

        menuItemListarServicios.addActionListener(mostrarVistaAction);
        menuItemInsertarServicios.addActionListener(mostrarVistaAction);
        menuItemModificarServicios.addActionListener(mostrarVistaAction);
        menuItemEliminarServicios.addActionListener(mostrarVistaAction);
    }

    public static void clients(JMenuBar menuBar, JFrame frame){
        JMenu menuClientes = new JMenu("Clientes");

        JMenuItem menuItemListarClientes = new JMenuItem("Listar clientes");
        JMenuItem menuItemInsertarClientes = new JMenuItem("Insertar clientes");
        JMenuItem menuItemModificarClientes = new JMenuItem("Modificar clientes");
        JMenuItem menuItemEliminarClientes = new JMenuItem("Eliminar clientes");

        menuClientes.add(menuItemListarClientes);
        menuClientes.add(menuItemInsertarClientes);
        menuClientes.add(menuItemModificarClientes);
        menuClientes.add(menuItemEliminarClientes);

        menuBar.add(menuClientes);

        menuItemListarClientes.addActionListener(e -> {

        });

        menuItemInsertarClientes.addActionListener(e -> {

        });

        menuItemModificarClientes.addActionListener(e -> {

        });

        menuItemEliminarClientes.addActionListener(e -> {

        });
    }

    public static void tickets(JMenuBar menuBar, JFrame frame){
        TicketModel model = new TicketModel();

        JMenu menuTickets = new JMenu("Tickets");

        JMenuItem menuItemListarTickets = new JMenuItem("Listar tickets");
        JMenuItem menuItemInsertarTickets = new JMenuItem("Insertar tickets");
        JMenuItem menuItemModificarTickets = new JMenuItem("Modificar tickets");
        JMenuItem menuItemEliminarTickets = new JMenuItem("Eliminar tickets");

        menuTickets.add(menuItemListarTickets);
        menuTickets.add(menuItemInsertarTickets);
        menuTickets.add(menuItemModificarTickets);
        menuTickets.add(menuItemEliminarTickets);

        menuBar.add(menuTickets);

        ActionListener mostrarVistaAction = e -> {
            JMenuItem menuItem = (JMenuItem) e.getSource();
            JPanel nuevaVista = null;

            switch (menuItem.getText()) {
                case "Listar tickets":
                    nuevaVista = new MostrarTicketView();
                    new MostrarTicketController((MostrarTicketView) nuevaVista, model);
                    break;
                case "Insertar tickets":
                    nuevaVista = new GenerarTicketView();
                    new GenerarTicketController((GenerarTicketView) nuevaVista, model);
                    break;
                case "Modificar tickets":
                    nuevaVista = new ModificarTicketView();
                    new ModificarTicketController((ModificarTicketView)nuevaVista, model);
                    break;
                case "Eliminar tickets":
                    nuevaVista = new EliminarTicketView();
                    new EliminarTicketController((EliminarTicketView) nuevaVista, model);
                    break;
                default:
                    nuevaVista = new GenerarTicketView();
                    new GenerarTicketController((GenerarTicketView) nuevaVista, model);
                    break;
            }

            if (nuevaVista != null) {
                mostrarVista(frame, nuevaVista);
            }
        };

        menuItemListarTickets.addActionListener(mostrarVistaAction);
        menuItemInsertarTickets.addActionListener(mostrarVistaAction);
        menuItemModificarTickets.addActionListener(mostrarVistaAction);
        menuItemEliminarTickets.addActionListener(mostrarVistaAction);
    }
}
