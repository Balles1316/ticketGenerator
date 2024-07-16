package Vista.Ticket;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class MostrarTicketView extends JPanel {
    private DefaultTableModel modeloTabla;
    private JTable tablaTicket;
    private JTextField txtNumeroTicket, txtServicio, txtProducto, txtPrecioConIVA, txtCliente, txtFecha;
    private JComboBox<String> comboMetodoPago;
    private JButton btnBuscarTicket;

    public MostrarTicketView() {
        setLayout(new BorderLayout());
        inicializarComponentes();
    }

    private void inicializarComponentes() {
        JPanel panelFiltro = new JPanel(new BorderLayout());
        panelFiltro.setBorder(BorderFactory.createTitledBorder("Ticket Filtro"));

        JPanel panelFiltro1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel panelFiltro2 = new JPanel(new FlowLayout(FlowLayout.LEFT));

        txtNumeroTicket = new JTextField(6);
        txtServicio = new JTextField(6);
        txtProducto = new JTextField(6);
        txtPrecioConIVA = new JTextField(6);
        txtCliente = new JTextField(6);
        comboMetodoPago = new JComboBox<>(new String[]{"Visa", "Metalico"});
        txtFecha = new JTextField(6);
        btnBuscarTicket = new JButton("Buscar");

        panelFiltro1.add(new JLabel("Numero Ticket"));
        panelFiltro1.add(txtNumeroTicket);
        panelFiltro1.add(new JLabel("Servicio"));
        panelFiltro1.add(txtServicio);
        panelFiltro1.add(new JLabel("Producto"));
        panelFiltro1.add(txtProducto);

        panelFiltro2.add(new JLabel("Balance"));
        panelFiltro2.add(txtPrecioConIVA);
        panelFiltro2.add(new JLabel("Cliente"));
        panelFiltro2.add(txtCliente);
        panelFiltro2.add(new JLabel("MetodoPago"));
        panelFiltro2.add(comboMetodoPago);
        panelFiltro2.add(new JLabel("Fecha"));
        panelFiltro2.add(txtFecha);
        panelFiltro2.add(btnBuscarTicket);

        panelFiltro.add(panelFiltro1, BorderLayout.NORTH);
        panelFiltro.add(panelFiltro2, BorderLayout.CENTER);

        add(panelFiltro, BorderLayout.NORTH);

        String[] columnas = {"N-Ticket", "Servicio", "Producto", "Balance", "Cliente", "MetodoPago", "Fecha"};
        modeloTabla = new DefaultTableModel(columnas, 0);
        tablaTicket = new JTable(modeloTabla);
        tablaTicket.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scrollPane = new JScrollPane(tablaTicket);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Tabla Ticket"));
        add(scrollPane, BorderLayout.CENTER);
    }

    public DefaultTableModel getModeloTabla() {
        return modeloTabla;
    }

    public JTable getTablaTicket() {
        return tablaTicket;
    }

    public JTextField getTxtNumeroTicket() {
        return txtNumeroTicket;
    }

    public JTextField getTxtServicio() {
        return txtServicio;
    }

    public JTextField getTxtProducto() {
        return txtProducto;
    }

    public JTextField getTxtPrecioConIVA() {
        return txtPrecioConIVA;
    }

    public JTextField getTxtCliente() {
        return txtCliente;
    }

    public JTextField getTxtFecha() {
        return txtFecha;
    }

    public JComboBox<String> getComboMetodoPago() {
        return comboMetodoPago;
    }

    public JButton getBtnBuscarTicket() {
        return btnBuscarTicket;
    }

    public void mostrarMensaje(String message) {
        JOptionPane.showMessageDialog(this, message);
    }
}
