package Vista.Ticket;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class MostrarTicketView extends JPanel {
    private DefaultTableModel modeloTabla;
    private JTable tablaTicket;
    private JTextField txtNumeroTicket, txtServicio, txtProducto , txtCantidad, txtPrecioConIVA, txtCliente , txtMetodoPago;
    private JButton btnBuscarTicket;

    public MostrarTicketView() {
        setLayout(new BorderLayout());
        inicializarComponentes();
    }

    private void inicializarComponentes() {
        JPanel panelFiltro = new JPanel(new FlowLayout());
        panelFiltro.setBorder(BorderFactory.createTitledBorder("Ticket Filtro"));

        txtNumeroTicket = new JTextField( 3);
        txtServicio = new JTextField(6);
        txtProducto = new JTextField(6);
        txtCantidad = new JTextField(3);
        txtPrecioConIVA = new JTextField(6);
        txtCliente = new JTextField(6);
        btnBuscarTicket = new JButton("Buscar");

        panelFiltro.add(new JLabel("Numero Ticket"));
        panelFiltro.add(txtNumeroTicket);
        panelFiltro.add(new JLabel("Servicio"));
        panelFiltro.add(txtServicio);
        panelFiltro.add(new JLabel("Producto"));
        panelFiltro.add(txtProducto);
        panelFiltro.add(new JLabel("Cantidad"));
        panelFiltro.add(txtCantidad);
        panelFiltro.add(new JLabel("Precio ConIVA"));
        panelFiltro.add(txtPrecioConIVA);
        panelFiltro.add(new JLabel("Cliente"));
        panelFiltro.add(txtCliente);
        panelFiltro.add(btnBuscarTicket);

        add(panelFiltro, BorderLayout.NORTH);

        String[] columnas = {"N-Ticket", "Servicio" , "Producto" , "Cantidad", "PrecioIVA", "Cliente","MetodoPago","Fecha"};
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

    public void mostrarMensaje(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

}
