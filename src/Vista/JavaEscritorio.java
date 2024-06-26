package Vista;

import Controlador.JavaEscritorioControlador;
import Vista.Cliente.InsertarClienteView;
import Vista.Cliente.MostrarClienteView;
import Vista.Servicio.EliminarServiciosView;
import Vista.Servicio.InsertarServiciosView;
import Vista.Servicio.ModificarServiciosView;
import Vista.Servicio.MostrarServiciosView;
import Vista.tickets.EliminarTicketView;
import Vista.tickets.GenerarTicketView;
import Vista.tickets.ModificarTicketView;
import Vista.tickets.MostrarTicketView;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class JavaEscritorio extends JFrame {

    private JavaEscritorioControlador controlador;

    // Componentes del menú
    private CardLayout cardLayout;
    private JPanel cardPanel;
    private JMenuBar menuBar;
    private JMenu menuTicket, menuServicios, menuCliente, menuAyuda;
    private JMenuItem menuItemGenerarTicket, menuItemMostrarTicket , menuItemModificarTickets, menuItemEliminarTickets;
    private JMenuItem menuItemEditarClientes , menuItemMostrarClientes ;
    private JMenuItem menuItemListarServicios, menuItemInsertar, menuItemModificar, menuItemEliminar ;
    private JMenuItem menuItemMasAyuda ;

    private Map<String, JPanel> panelsMap;

    public JavaEscritorio() {
        super("Parabeus S.L. Escritorio");

        panelsMap = new HashMap<>();

        inicializarVentana();
        inicializarMenu();
        inicializarPaneles();
    }

    private void inicializarVentana() {
        // Configuración básica de la ventana
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void inicializarMenu() {
        menuBar = new JMenuBar();
        menuTicket = new JMenu("Tickets");
        menuServicios = new JMenu("Servicios");
        menuCliente = new JMenu("Cliente");
        menuAyuda = new JMenu("Ayuda");

        menuBar.add(menuTicket);
        menuBar.add(menuCliente);
        menuBar.add(menuServicios);
        menuBar.add(menuAyuda);

        // SUBMENU SERVICIOS
        menuItemListarServicios = new JMenuItem("Listar Servicios");
        menuItemListarServicios.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "Listar Servicios");
            }
        });
        menuServicios.add(menuItemListarServicios);

        menuItemInsertar = new JMenuItem("Insertar Servicio");
        menuItemInsertar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "Insertar Servicio");
            }
        });
        menuServicios.add(menuItemInsertar);

        menuItemModificar = new JMenuItem("Modificar Servicio");
        menuItemModificar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "Modificar Servicio");
            }
        });
        menuServicios.add(menuItemModificar);

        menuItemEliminar = new JMenuItem("Eliminar Servicio");
        menuItemEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "Eliminar Servicio");
            }
        });
        menuServicios.add(menuItemEliminar);

        //SubMenu Crear Ticket
        menuItemGenerarTicket = new JMenuItem("Generar Ticket");
        menuItemGenerarTicket.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controlador = new JavaEscritorioControlador(JavaEscritorio.this);
                controlador.guardarPrecios();
                controlador.cargarPrecios();
                controlador.actualizarComboBoxCodigoServicio();
                cardLayout.show(cardPanel, "Generar Ticket");
            }
        });
        menuTicket.add(menuItemGenerarTicket);

        //SubMenu Mostrar
        menuItemMostrarTicket = new JMenuItem("Mostrar Ticket");
        menuItemMostrarTicket.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "Listar Ticket");
            }
        });
        menuTicket.add(menuItemMostrarTicket);

        //SubMenu Modificar
        menuItemModificarTickets = new JMenuItem("Modificar Ticket");
        menuItemModificarTickets.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "Modificar Ticket");
            }
        });
        menuTicket.add(menuItemModificarTickets);

        //SubMenu Eliminar
        menuItemEliminarTickets = new JMenuItem("Eliminar Ticket");
        menuItemEliminarTickets.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "Eliminar Ticket");
            }
        });
        menuTicket.add(menuItemEliminarTickets);

        //SubMenu Cliente
        menuItemEditarClientes = new JMenuItem("Añadir Clientes");
        menuItemEditarClientes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "Añadir Clientes");
            }
        });
        menuCliente.add(menuItemEditarClientes);

        menuItemMostrarClientes = new JMenuItem("Mostrar Clientes");

        menuItemMostrarClientes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "Mostrar Clientes");
            }
        });
        menuCliente.add(menuItemMostrarClientes);

        menuItemMasAyuda = new JMenuItem("Más Ayuda");
        menuItemMasAyuda.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(
                        JavaEscritorio.this,
                        "Para obtener más ayuda contáctenos en:\nGitHub - Balles1316\nGitHub - blue-c0de",
                        "Ayuda",
                        JOptionPane.INFORMATION_MESSAGE
                );
            }
        });
        menuAyuda.add(menuItemMasAyuda);

        setJMenuBar(menuBar);
    }

    private void inicializarPaneles() {
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        // Create panels and add them to the cardPanel and panelsMap
        GenerarTicketView generarTicketView = new GenerarTicketView(this);
        MostrarTicketView mostrarTicketView = new MostrarTicketView(this);
        ModificarTicketView modificarTicketView = new ModificarTicketView(this);
        EliminarTicketView eliminarTicketView = new EliminarTicketView(this);
        MostrarServiciosView mostrarServiciosView = new MostrarServiciosView(this);
        InsertarServiciosView insertarServiciosView = new InsertarServiciosView(this);
        ModificarServiciosView modificarServiciosView = new ModificarServiciosView(this);
        EliminarServiciosView eliminarServiciosView = new EliminarServiciosView(this);
        InsertarClienteView panelDatosCliente = new InsertarClienteView();
        MostrarClienteView panelMostrarCliente = new MostrarClienteView(this);

        cardPanel.add(generarTicketView, "Generar Ticket");
        cardPanel.add(mostrarTicketView, "Listar Ticket");
        cardPanel.add(modificarTicketView, "Modificar Ticket");
        cardPanel.add(eliminarTicketView, "Eliminar Ticket");

        cardPanel.add(mostrarServiciosView, "Listar Servicios");
        cardPanel.add(insertarServiciosView, "Insertar Servicio");
        cardPanel.add(modificarServiciosView, "Modificar Servicio");
        cardPanel.add(eliminarServiciosView, "Eliminar Servicio");
        cardPanel.add(panelDatosCliente, "Añadir Clientes");
        cardPanel.add(panelMostrarCliente, "Mostrar Clientes");

        panelsMap.put("Generar Ticket", generarTicketView);
        panelsMap.put("Listar Ticket", mostrarTicketView);
        panelsMap.put("Modificar Ticket", modificarTicketView);
        panelsMap.put("Eliminar Ticket", eliminarTicketView);
        panelsMap.put("Listar Servicios", mostrarServiciosView);
        panelsMap.put("Insertar Servicio", insertarServiciosView);
        panelsMap.put("Modificar Servicio", modificarServiciosView);
        panelsMap.put("Eliminar Servicio", eliminarServiciosView);
        panelsMap.put("Añadir Clientes", panelDatosCliente);
        panelsMap.put("Mostrar Clientes", panelMostrarCliente);

        add(cardPanel);
    }

    public DefaultTableModel getModeloTabla() {
        return ((MostrarServiciosView) panelsMap.get("Listar Servicios")).getModeloTabla();
    }

    public JTable getTable() {
        return ((MostrarServiciosView) panelsMap.get("Listar Servicios")).getTablaPrecios();
    }

    public JButton getBtnImprimir() {
        return ((GenerarTicketView) panelsMap.get("Generar Ticket")).getBtnImprimir();
    }

    public void actualizarComboBoxCodigoServicio() {
        ((GenerarTicketView) panelsMap.get("Generar Ticket")).actualizarComboBoxCodigoServicio(getModeloTabla());
    }
}