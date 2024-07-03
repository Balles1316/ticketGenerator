package Vista.Ticket;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class GenerarTicketView extends JPanel {
    private JTextField txtNumeroTicket, txtCantidad, txtProducto, txtPrecioConIVA, txtCliente, txtClienteEncontrado;
    private JComboBox<String> comboServicios;
    private JButton btnImprimir, btnBuscar;
    private JRadioButton jRadiometalico, jRadioVisa;

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
        jRadioVisa = new JRadioButton("Tarjeta Crédito/Débito");

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
    }


    public JComboBox<String> getComboServicios() {
        return comboServicios;
    }

    public void guardarListenerImprimir(ActionListener listener) {
        btnImprimir.addActionListener(listener);
    }

    public void guardarListenerJComboBox(ActionListener listener) {
        comboServicios.addActionListener(listener);
    }

    public String getNumeroTicket() {
        return txtNumeroTicket.getText().trim();
    }

    public String getServicio() {
        return comboServicios.getSelectedItem().toString().trim();
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

    public JTextField getTxtPrecioConIVA() {
        return txtPrecioConIVA;
    }

    public void setTxtPrecioConIVA(JTextField txtPrecioConIVA) {
        this.txtPrecioConIVA = txtPrecioConIVA;
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
    }
}
