package Vista.Ticket;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class GenerarTicketView extends JPanel {
    private JTextField txtNumeroTicket, txtCantidad, txtProducto, txtBalanceConIVA, txtCliente, txtClienteEncontrado;
    private JComboBox<String> comboServicios;
    private JButton btnImprimir, btnBuscar;
    private JRadioButton jRadiometalico, jRadioVisa;
    private JRadioButton jRadioPapel, jRadioEmail;

    public GenerarTicketView() {
        setLayout(new BorderLayout());
        inicializarComponentes();
    }

    private void inicializarComponentes() {
        JPanel panelFormulario = new JPanel(new GridLayout(10, 2, 10, 10));
        panelFormulario.setBorder(BorderFactory.createTitledBorder("Generar Ticket"));

        txtNumeroTicket = new JTextField();
        txtNumeroTicket.setEditable(false); // Hacer el campo no editable
        txtNumeroTicket.setHorizontalAlignment(JTextField.RIGHT); // Alinear a la derecha
        comboServicios = new JComboBox<>();
        txtCantidad = new JTextField("1");
        txtProducto = new JTextField("Champu Normal");
        txtBalanceConIVA = new JTextField();
        txtCliente = new JTextField();
        btnBuscar = new JButton("Buscar");
        txtClienteEncontrado = new JTextField("Anonimo");
        btnImprimir = new JButton("Imprimir");

        jRadiometalico = new JRadioButton("Metalico");
        jRadioVisa = new JRadioButton("Tarjeta Crédito/Débito");

        jRadioPapel = new JRadioButton("Papel");
        jRadioEmail = new JRadioButton("Correo Electronico");

        // Set jRadioVisa as the default selected option
        jRadioVisa.setSelected(true);

        // Set jRadioPapel as the default selected option
        jRadioPapel.setSelected(true);

        ButtonGroup bGroup = new ButtonGroup();
        bGroup.add(jRadiometalico);
        bGroup.add(jRadioVisa);

/*
        ButtonGroup b2Group = new ButtonGroup();
        b2Group.add(jRadioPapel);
        b2Group.add(jRadioEmail);
*/

        panelFormulario.add(new JLabel("Numero Ticket:"));
        panelFormulario.add(txtNumeroTicket);
        panelFormulario.add(new JLabel("Servicio:"));
        panelFormulario.add(comboServicios);
        panelFormulario.add(new JLabel("Cantidad:"));
        panelFormulario.add(txtCantidad);
        panelFormulario.add(new JLabel("Producto:"));
        panelFormulario.add(txtProducto);
        panelFormulario.add(new JLabel("Balance :"));
        panelFormulario.add(txtBalanceConIVA);
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

        panelFormulario.add(new JLabel("Forma Impresión Ticket"));

        JPanel metodoImpresionPanel = new JPanel(new FlowLayout());
        metodoImpresionPanel.add(jRadioPapel);
        metodoImpresionPanel.add(jRadioEmail);
        panelFormulario.add(metodoImpresionPanel);

        add(panelFormulario, BorderLayout.CENTER);

        JPanel panelBoton = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelBoton.add(btnImprimir);
        add(panelBoton, BorderLayout.SOUTH);
    }

    public JTextField getTxtNumeroTicket() {
        return txtNumeroTicket;
    }

    public void setTxtNumeroTicket(String numeroTicket) {
        txtNumeroTicket.setText(numeroTicket);
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

    public String getBalanceConIVA() {
        return txtBalanceConIVA.getText().trim();
    }

    public JTextField getTxtBalanceConIVA() {
        return txtBalanceConIVA;
    }

    public void settxtBalanceConIVA(JTextField txtBalanceConIVA) {
        this.txtBalanceConIVA = txtBalanceConIVA;
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

    public String getFormaImpresion() {
        if (jRadioPapel.isSelected()) {
            return "Papel";
        } else if (jRadioEmail.isSelected()) {
            return "Email";
        }else if (jRadioEmail.isSelected() && jRadioPapel.isSelected()) {
            return "PapelEmail";
        }
        return "";
    }

    /**
     * Muestra un mensaje en una ventana emergente.
     * @param message Que quieras mostrar en la ventana emergente
     */
    public void mostrarMensaje(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    /**
     * Muestra un cuadro de diálogo con un campo de texto para ingresar texto.
     * @param mensaje que se mostrara en el cuadro de dialogo
     * @return el texto introducido por el usuario, o null si el usuario cancela el cuadro de diálogo
     */
    public String mostrarDialogo(String mensaje) {
        // Crear el campo de texto
        JTextField textField = new JTextField();

        // Crear un panel que contenga el mensaje y el JTextField
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(new JLabel(mensaje));
        panel.add(textField);

        // Mostrar el cuadro de diálogo con el campo de texto
        int resultado = JOptionPane.showConfirmDialog(null, panel, "Input", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        // Si el usuario presiona "OK", retornar el texto introducido
        if (resultado == JOptionPane.OK_OPTION) {
            return textField.getText();
        } else {
            return null; // Si el usuario cancela, retornar null
        }
    }

    /**
     * Limpia los campos de texto
     */
    public void limpiarCampos() {
        txtCantidad.setText("1");
        txtBalanceConIVA.setText("");
        txtCliente.setText("");
    }
}
