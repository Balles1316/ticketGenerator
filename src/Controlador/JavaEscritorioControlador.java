package Controlador;

import Vista.JavaEscritorio;

import java.awt.*;
import java.awt.event.*;
import java.awt.print.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class JavaEscritorioControlador extends WindowAdapter implements Printable {
    private JavaEscritorio vista;
    private DefaultTableModel modeloTabla;
    private JTable tablaPrecios;
    private List<String> itemName;
    private List<String> itemPrice;
    private List<String> quantity;
    private List<String> subtotal;
    private JTextField txttotalAmount;
    private JTextField txtcash;
    private String balance;
    private String ticketnumber;
    private Container rootPane;

    public JavaEscritorioControlador(JavaEscritorio vista) {
        this.vista = vista;
        this.vista.addWindowListener(this);
        modeloTabla = vista.getModeloTabla();
        tablaPrecios = vista.getTable();

        itemName = new ArrayList<>();
        itemPrice = new ArrayList<>();
        quantity = new ArrayList<>();
        subtotal = new ArrayList<>();

        agregarOyentesDeEventos();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JavaEscritorio vista = new JavaEscritorio();
                new JavaEscritorioControlador(vista);
                vista.setVisible(true);
            }
        });
    }

    private void agregarOyentesDeEventos() {
        vista.getBtnAgregar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modeloTabla.addRow(new Object[]{"", ""});
                int fila = modeloTabla.getRowCount() - 1;
                int columna = 0;
                tablaPrecios.editCellAt(fila, columna);
                Component editor = tablaPrecios.getEditorComponent();
                if (editor != null) {
                    editor.requestFocus();
                }
            }
        });

        vista.getBtnEliminar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int filaSeleccionada = tablaPrecios.getSelectedRow();
                if (filaSeleccionada >= 0) {
                    modeloTabla.removeRow(filaSeleccionada);
                } else {
                    JOptionPane.showMessageDialog(null, "Por favor, selecciona un producto para eliminar.");
                }
                guardarPrecios();
            }
        });

        vista.getBtnImprimir().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                for (String item : itemName){
                    System.out.println(item);
                }

                // Create a PrinterJob instance
                PrinterJob job = PrinterJob.getPrinterJob();

                // Set the Printable instance (JavaEscritorioControlador) for printing
                job.setPrintable(JavaEscritorioControlador.this);

                // Show print dialog
                boolean doPrint = job.printDialog();

                if (doPrint) {
                    try {
                        // Perform the actual print operation
                        job.print();
                        // Clear lists after printing
                        limpiarListas();
                    } catch (PrinterException ex) {
                        JOptionPane.showMessageDialog(null, "Error al imprimir: " + ex.getMessage());
                    }
                }
            }
        });

    }

    public void guardarPrecios() {
        Properties propiedades = new Properties();

        for (int i = 0; i < modeloTabla.getRowCount(); i++) {
            String producto = (String) modeloTabla.getValueAt(i, 0);
            String precio = (String) modeloTabla.getValueAt(i, 1);
            propiedades.setProperty(producto, precio);
        }

        try {
            propiedades.store(new FileOutputStream("precios.properties"), "Precios de Productos");
            System.out.println("Precios guardados correctamente.");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error al guardar los precios.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void cargarPrecios() {
        Properties propiedades = new Properties();

        try {
            propiedades.load(new FileInputStream("precios.properties"));
            if (tablaPrecios == null || tablaPrecios.getModel() != modeloTabla) {
                tablaPrecios.setModel(modeloTabla);
            }
            tablaPrecios.setEnabled(true);
            tablaPrecios.setDefaultEditor(Object.class, new DefaultCellEditor(new JTextField()));
            modeloTabla.setRowCount(0);
            for (String key : propiedades.stringPropertyNames()) {
                String precio = propiedades.getProperty(key);
                modeloTabla.addRow(new Object[]{key, precio});
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error al cargar los precios.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void limpiarListas() {
        itemName.clear();
        itemPrice.clear();
        quantity.clear();
        subtotal.clear();
    }

    public void actualizarComboBoxCodigoServicio() {
        vista.actualizarComboBoxCodigoServicio();
    }

    // Getters y setters para las listas
    public List<String> getItemName() {
        return itemName;
    }

    public void addItemName(String name) {
        this.itemName.add(name);
    }

    public List<String> getItemPrice() {
        return itemPrice;
    }

    public void addItemPrice(String price) {
        this.itemPrice.add(price);
    }

    public List<String> getQuantity() {
        return quantity;
    }

    public void addQuantity(String qty) {
        this.quantity.add(qty);
    }

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

        // Assuming rootPane is correctly initialized, if not you may need to update this
        ImageIcon icon = new ImageIcon("C:\\PARABEUS.jpg");
        g2d.drawImage(icon.getImage(), 50, 20, 90, 30, rootPane);
        y += yShift + 30;
        g2d.drawString("-------------------------------------", 10, y);
        y += yShift;
        g2d.drawString("         Parabeus S.L        ", 10, y);
        y += yShift;
        g2d.drawString("       Ticket number " + ticketnumber, 10, y);
        y += yShift;
        g2d.drawString("       Calle Sagasta n 15    ", 10, y);
        y += yShift;
        g2d.drawString("       +34 915 21 48 86      ", 10, y);
        y += yShift;
        g2d.drawString("-------------------------------------", 10, y);
        y += headerRectHeight;

        g2d.drawString(" Item Name                  Price   ", 10, y);
        y += yShift;
        g2d.drawString("-------------------------------------", 10, y);
        y += headerRectHeight;

        for (int s = 0; s < itemName.size(); s++) {
            System.out.println(itemName.get(s));
            g2d.drawString(" " + itemName.get(s) + "                            ", 10, y);
            y += yShift;
            g2d.drawString("      " + quantity.get(s) + " * " + itemPrice.get(s), 10, y);
            //g2d.drawString(subtotal.get(s), 160, y);
            g2d.drawString(itemPrice.get(s), 160, y);
            y += yShift;
        }

        g2d.drawString("-------------------------------------", 10, y);
        y += yShift;
        g2d.drawString(" Total amount:               " /*+ txttotalAmount.getText()*/ + "   ", 10, y);
        y += yShift;
        g2d.drawString("-------------------------------------", 10, y);
        y += yShift;
        g2d.drawString(" Cash      :                 " + /*txtcash.getText() +*/ "   ", 10, y);
        y += yShift;
        g2d.drawString("-------------------------------------", 10, y);
        y += yShift;
        g2d.drawString(" Balance   :                 " + /*balance +*/ "   ", 10, y);
        y += yShift;

        g2d.drawString("*************************************", 10, y);
        y += yShift;
        g2d.drawString("       THANK YOU COME AGAIN          ", 10, y);
        y += yShift;
        g2d.drawString("*************************************", 10, y);
        y += yShift;
        g2d.drawString("       SOFTWARE BY:PARABEUS          ", 10, y);
        y += yShift;
        g2d.drawString("CONTACT: parabeuspeluqueria@gmail.com", 10, y);
        y += yShift;

        return PAGE_EXISTS;
    }

    @Override
    public void windowOpened(WindowEvent e) {
        cargarPrecios();
        vista.actualizarComboBoxCodigoServicio();
    }

    @Override
    public void windowClosing(WindowEvent e) {
        guardarPrecios();
    }

    @Override
    public void windowClosed(WindowEvent e) {
        guardarPrecios();
    }
}
