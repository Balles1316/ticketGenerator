package Vista;

import Controlador.JavaEscritorioControlador;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JavaEscritorio extends JFrame {

    private JavaEscritorioControlador controlador;

    // Componentes del menú
    private CardLayout cardLayout;
    private JPanel cardPanel;
    private JMenuBar menuBar;
    private JMenu menuTicket, menuServicios, menuCliente, menuAyuda;
    private JMenuItem menuItemGenerarTicket, menuItemEditarPrecios;
    private JMenuItem menuItemEditarClientes , menuItemMostrarClientes ;
    private JMenuItem menuItemMasAyuda, menuItemInsertar, menuItemModificar ;

    public JavaEscritorio() {
        super("Parabeus S.L. Escritorio");

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


        //SubMenu Ticket
        menuItemGenerarTicket = new JMenuItem("Generar Ticket");
        menuItemGenerarTicket.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controlador = new JavaEscritorioControlador(JavaEscritorio.this);
                controlador.guardarPrecios();
                controlador.cargarPrecios();
                controlador.actualizarComboBoxCodigoServicio();
                cardLayout.show(cardPanel, "GenerarTicket");
            }
        });
        menuTicket.add(menuItemGenerarTicket);

        menuItemEditarPrecios = new JMenuItem("Editar Precios");
        menuItemEditarPrecios.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "EditarPrecios");
            }
        });
        menuTicket.add(menuItemEditarPrecios);

        //SubMenu Cliente
        menuItemEditarClientes = new JMenuItem("Anadir Clientes");
        menuItemEditarClientes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "AnadirClientes");
            }
        });
        menuCliente.add(menuItemEditarClientes);

        menuItemMostrarClientes = new JMenuItem("Mostrar Clientes");
        
        menuItemMostrarClientes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "MostrarClientes");
            }
        });
        menuCliente.add(menuItemMostrarClientes);

        menuItemMasAyuda = new JMenuItem("Mas Ayuda");
        menuItemMasAyuda.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(
                    JavaEscritorio.this,
                    "Para obtener más ayuda contactenos en:\nGitHub - Balles1316\nGitHub - blue-c0de",
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

        // Agregar paneles al cardPanel
        cardPanel.add(new PanelGenerarTicket(this), "GenerarTicket");
        cardPanel.add(new PanelEditarPrecios(this), "EditarPrecios");
        cardPanel.add(new InsertarServiciosView(this), "Insertar Servicio");
        cardPanel.add(new ModificarServiciosView(this), "Modificar Servicio");
        cardPanel.add(new PanelDatosCliente(this), "AnadirClientes");
        cardPanel.add(new PanelMostrarCliente(this), "MostrarClientes");

        add(cardPanel);
    }

    public DefaultTableModel getModeloTabla() {
        return ((PanelEditarPrecios) cardPanel.getComponent(1)).getModeloTabla();
    }

    public JTable getTable() {
        return ((PanelEditarPrecios) cardPanel.getComponent(1)).getTablaPrecios();
    }

    public JButton getBtnImprimir() {
        return ((PanelGenerarTicket) cardPanel.getComponent(0)).getBtnImprimir();
    }

    public JButton getBtnAgregar() {
        return ((PanelEditarPrecios) cardPanel.getComponent(1)).getBtnAgregar();
    }

    public JButton getBtnEliminar() {
        return ((PanelEditarPrecios) cardPanel.getComponent(1)).getBtnEliminar();
    }

    public void actualizarComboBoxCodigoServicio() {
        ((PanelGenerarTicket) cardPanel.getComponent(0)).actualizarComboBoxCodigoServicio(getModeloTabla());
    }
}
