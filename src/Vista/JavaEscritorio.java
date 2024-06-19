package Vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import Controlador.JavaEscritorioControlador;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

/**
 * The JavaEscritorio class represents a JFrame window for the Parabeus S.L application.
 * It contains components for menu, interface generation, and table.
 * The class provides methods for initializing the window, menu, and panels.
 * It also includes methods for updating the combo box and cleaning the input fields.
 */
public class JavaEscritorio extends JFrame {

    private JavaEscritorioControlador controlador;
	
    //Componentes del menu
    private CardLayout cardLayout;
    private JPanel cardPanel;
    private JMenuBar menuBar;
    private JMenu menuTicket , menuCliente , menuAyuda;
    private JMenuItem menuItemGenerarTicket, menuItemEditarPrecios;
    private JButton btnImprimir ;
    
	//Componentes de la interfaz generacionTicket
    private JTextField txtNumeroTicket, txtCodigoServicio, txtCantidad, txtPrecioConIVA;
    private JComboBox<String> comboServicios;
    private JButton btnAgregar, btnEliminar, btnPrint, btnNewTicket, btnCerrarCaja;
    
    //Componentes Tabla 
    private DefaultTableModel modeloTabla;
    private JTable tablaPrecios;
    
    public JavaEscritorio() {
    	
        super("Parabeus S.L");

        inicializarVentana();
        inicializarMenu();
        inicializarPaneles();

    }

    private void inicializarVentana() {
        // Configuraci�n b�sica de la ventana
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void inicializarMenu() {
    	
        menuBar = new JMenuBar();
        menuTicket = new JMenu("Tickets");
        menuCliente = new JMenu("Cliente");
        menuAyuda = new JMenu("Ayuda");
        
        menuBar.add(menuTicket);
        menuBar.add(menuCliente);
        menuBar.add(menuAyuda);

        menuItemGenerarTicket = new JMenuItem("Generar Ticket");
        menuItemGenerarTicket.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Initialize the controlador object
                controlador = new JavaEscritorioControlador(JavaEscritorio.this);
                
                //Para el ComboBox
                controlador.guardarPrecios();
                controlador.cargarPrecios();
                actualizarComboBoxCodigoServicio();
                //Mostramso La vista
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

        
        setJMenuBar(menuBar);
    }

    private void inicializarPaneles() {
    	
        tablaPrecios = new JTable(); // Inicializa tablaPrecios

        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        // Panel para Generar Ticket

        JPanel pnlDatosTicket = new JPanel(new GridLayout(5, 2, 10, 10));
        pnlDatosTicket.setBorder(BorderFactory.createTitledBorder("Datos del Ticket"));

        txtNumeroTicket = new JTextField();
        txtCantidad = new JTextField("1");
        txtPrecioConIVA = new JTextField();
        comboServicios = new JComboBox<>();
        btnImprimir = new JButton("Imprimir");
        
        pnlDatosTicket.add(new JLabel("N�mero de Ticket:"));
        pnlDatosTicket.add(txtNumeroTicket);
        pnlDatosTicket.add(new JLabel("C�digo Servicio:"));
        pnlDatosTicket.add(comboServicios);
        pnlDatosTicket.add(new JLabel("Cantidad:"));
        pnlDatosTicket.add(txtCantidad);
        pnlDatosTicket.add(new JLabel("Precio con IVA:"));
        pnlDatosTicket.add(txtPrecioConIVA);
        pnlDatosTicket.add(new JLabel(""));
        pnlDatosTicket.add(btnImprimir);
        cardPanel.add(pnlDatosTicket, "GenerarTicket");
        
        //Listener JcokmBox pasar a el controlador en futuras actualizaciones
        comboServicios.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	
	            if (comboServicios.getSelectedItem() != null) {
	
	            	for (int i = 0; i < modeloTabla.getRowCount(); i++) {
	            		
	            		if(comboServicios.getSelectedItem().toString().equals(modeloTabla.getValueAt(i, 0))) {
	            			
	            			txtPrecioConIVA.setText((String) modeloTabla.getValueAt(i, 1));
	            			
	            		}
	            	}         
	            }

            }
        });

        // Panel para editar Precios

        JPanel panelMostrarTabla = new JPanel(new BorderLayout());
        String[] columnas = {"Producto", "Precio"};
        modeloTabla = new DefaultTableModel(columnas, 0);
        tablaPrecios = new JTable(modeloTabla);
        panelMostrarTabla.add(new JScrollPane(tablaPrecios),BorderLayout.CENTER);
        
        // Panel para los botones dentro de la tabla
        
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER));
        
         // Bot�n para a�adir un nuevo producto y precio
        btnAgregar = new JButton("A�adir Producto");
    
        // Bot�n para eliminar un producto seleccionado
        btnEliminar = new JButton("Eliminar Producto");
  
        panelBotones.add(btnAgregar);
        panelBotones.add(btnEliminar);
        
        panelMostrarTabla.add(panelBotones, BorderLayout.SOUTH); // Agregar el panel de botones debajo de la tabla
 
        cardPanel.add(panelMostrarTabla, "EditarPrecios");
        
        // Panel para a;adir clientes

        JPanel pnlDatosCliente = new JPanel(new BorderLayout());
 
        cardPanel.add(pnlDatosCliente, "DatosCliente");
        
        add(cardPanel);
       
    }
    
    public void actualizarComboBoxCodigoServicio() {
        Set<String> codigoServicioSet = new HashSet<>();
        for (int i = 0; i < modeloTabla.getRowCount(); i++) {
            String codigoServicio = (String) modeloTabla.getValueAt(i, 0); // Suponiendo que el c�digo de servicio est� en la primera columna
            System.out.println(codigoServicio);
            codigoServicioSet.add(codigoServicio);
        }
        comboServicios.removeAllItems();
        for (String codigoServicio : codigoServicioSet) {
        	comboServicios.addItem(codigoServicio);
        }

    }

    private void cleanALL()
    {
        //ticketnumber=0;
        txtNumeroTicket.setText("0");
        txtCantidad.setText("1");
        txtPrecioConIVA.setText("");
    }

    public DefaultTableModel getModeloTabla() {
        return modeloTabla;
    }

    public JTable getTable() {
        return tablaPrecios;
    }

    public JButton getBtnImprimir() {
        return btnImprimir;
    }

    public JButton getBtnAgregar() {
        return btnAgregar;
    }

    public JButton getBtnEliminar() {
        return btnEliminar;
    }

}
