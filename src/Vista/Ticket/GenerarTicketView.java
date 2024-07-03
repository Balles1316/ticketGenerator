package Vista.Ticket;

import Objeto.Ticket;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GenerarTicketView extends JPanel {
    private JTextField txtNumeroTicket, txtCantidad, txtProducto ,txtPrecioConIVA, txtCliente, txtClienteEncontrado;
    private JComboBox<String> comboServicios;
    private JButton btnImprimir, btnBuscar;
    private JRadioButton jRadiometalico, jRadioVisa ;
    public List<Ticket> tickets;

    public GenerarTicketView() {
        setLayout(new BorderLayout());
        inicializarComponentes();
    }

    private void inicializarComponentes() {
        JPanel panelFormulario = new JPanel(new GridLayout(8, 2, 10, 10));
        panelFormulario.setBorder(BorderFactory.createTitledBorder("Generar Ticket"));

        txtNumeroTicket = new JTextField("1");
        comboServicios = new JComboBox<>();
        txtCantidad = new JTextField("1");
        txtProducto = new JTextField("Champu Normal");
        txtPrecioConIVA = new JTextField();
        txtCliente = new JTextField();
        btnBuscar = new JButton("Buscar");
        txtClienteEncontrado = new JTextField("Anonimo");
        btnImprimir = new JButton("Imprimir");
        jRadiometalico = new JRadioButton("Metalico");
        jRadioVisa = new JRadioButton("Tarjeta Crédito/Débito ");

        // Set jRadioVisa as the default selected option
        jRadioVisa.setSelected(true);

        ButtonGroup bGroup = new ButtonGroup();
        bGroup.add(jRadiometalico);
        bGroup.add(jRadioVisa);

        panelFormulario.add(new JLabel("Numero Ticket:"));
        panelFormulario.add(txtNumeroTicket);
        panelFormulario.add(new JLabel("Servicio:"));
        panelFormulario.add(comboServicios);
        panelFormulario.add(new JLabel("Cantidad:"));
        panelFormulario.add(txtCantidad);
        panelFormulario.add(new JLabel("Producto:"));
        panelFormulario.add(txtProducto);
        panelFormulario.add(new JLabel("Precio ConIVA:"));
        panelFormulario.add(txtPrecioConIVA);
        panelFormulario.add(new JLabel("Cliente:"));
        JPanel clientePanel = new JPanel(new BorderLayout());
        clientePanel.add(txtCliente, BorderLayout.CENTER);
        clientePanel.add(btnBuscar, BorderLayout.EAST);
        panelFormulario.add(clientePanel);
        panelFormulario.add(new JLabel("Cliente Encontrado:"));
        panelFormulario.add(txtClienteEncontrado);
        panelFormulario.add(new JLabel("Metodo de Pago"));
        JPanel metodoPanel = new JPanel(new FlowLayout());
        metodoPanel.add(jRadiometalico);
        metodoPanel.add(jRadioVisa);
        panelFormulario.add(metodoPanel);

        add(panelFormulario, BorderLayout.CENTER);

        JPanel panelBoton = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelBoton.add(btnImprimir);
        add(panelBoton, BorderLayout.SOUTH);

        /*comboServicios.addActionListener(e -> {
            if (comboServicios.getSelectedItem() != null) {
                for (int i = 0; i < vista.getModeloTabla().getRowCount(); i++) {
                    if (comboServicios.getSelectedItem().toString().equals(vista.getModeloTabla().getValueAt(i, 0))) {
                        Object precioConIVAObj = vista.getModeloTabla().getValueAt(i, 1);
                        String precioConIVA = precioConIVAObj.toString(); // Convertir a String
                        txtPrecioConIVA.setText(precioConIVA);
                        try {
                            int cantidad = Integer.parseInt(txtCantidad.getText());
                            //addServicio(nTicket, comboServicios.getSelectedItem().toString(), cantidad, precioConIVA);
                        } catch (NumberFormatException ex) {
                            JOptionPane.showMessageDialog(this, "Cantidad debe ser un número entero.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                        break;
                    }
                }
            }
        });*/
    }

    public void actualizarComboBoxCodigoServicio(DefaultTableModel modeloTabla) {
        Set<String> codigoServicioSet = new HashSet<>();
        for (int i = 0; i < modeloTabla.getRowCount(); i++) {
            String codigoServicio = (String) modeloTabla.getValueAt(i, 0);
            codigoServicioSet.add(codigoServicio);
        }
        comboServicios.removeAllItems();
        for (String codigoServicio : codigoServicioSet) {
            comboServicios.addItem(codigoServicio);
        }
    }

    public JComboBox<String> getComboServicios() {
        return comboServicios;
    }

    public void guardarListener(ActionListener listener) {
        btnImprimir.addActionListener(listener);
    }

    public String getNumeroTicket() {
        return txtNumeroTicket.getText().trim();
    }

    public String getServicio() {
        return /*comboServicios.getSelectedItem().toString().trim();*/ "CorteEl" ;
    }

    public String getProducto() {
        return txtProducto.getText().trim();
    }

    public String getCantidad() {
        return txtCantidad.getText().trim();
    }

    public String getPrecioConIVA() {
        return txtPrecioConIVA.getText().trim();
    }

    public String getClienteEncontrado() {
        return txtClienteEncontrado.getText().trim();
    }

    public String getMetodoPago() {
        if (jRadiometalico.isSelected()) {
            return "Metalico";
        } else if (jRadioVisa.isSelected()) {
            return "Visa";
        }
        return "";
    }


    public void mostrarMensaje(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    public void limpiarCampos() {
        txtNumeroTicket.setText("");
        txtCantidad.setText("1");
        txtProducto.setText("");
        txtPrecioConIVA.setText("");
        txtCliente.setText("");
        txtClienteEncontrado.setText("");
/*
        comboServicios.setSelectedIndex(0); // Reinicia el combo box al primer elemento
*/
    }


}
