package Vista.Cliente;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class InsertarClienteView extends JPanel {
    private JTextField txtNombre, txtApellido, txtNacimiento, txtTelefono, txtEmail, txtCP;
    private JButton btnGuardar;

    private static final int INSETS_TOP = 10, INSETS_BOTTOM = 10, INSETS_LEFT = 10, INSETS_RIGHT = 10;
    private static final Dimension LABEL_SIZE = new Dimension(130, 25), TEXT_FIELD_SIZE = new Dimension(200, 25);

    public InsertarClienteView() {
        setLayout(new BorderLayout());
        initComponents();
    }

    private void initComponents() {
        JPanel panelPrincipal = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(INSETS_TOP, INSETS_LEFT, INSETS_BOTTOM, INSETS_RIGHT);

        JLabel lblTitulo = new JLabel("Insertar Clientes");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 24));
        gbc.gridx = 0;
        gbc.gridy = 0;
        panelPrincipal.add(lblTitulo, gbc);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints panelGbc = new GridBagConstraints();
        panelGbc.insets = new Insets(INSETS_TOP, INSETS_LEFT, INSETS_BOTTOM, INSETS_RIGHT);

        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setPreferredSize(LABEL_SIZE);
        panelGbc.gridx = 0;
        panelGbc.gridy = 0;
        panel.add(lblNombre, panelGbc);

        txtNombre = new JTextField();
        txtNombre.setPreferredSize(TEXT_FIELD_SIZE);
        panelGbc.gridx = 1;
        panelGbc.gridy = 0;
        panel.add(txtNombre, panelGbc);

        JLabel lblPrecio = new JLabel("Apellido:");
        lblPrecio.setPreferredSize(LABEL_SIZE);
        panelGbc.gridx = 0;
        panelGbc.gridy = 1;
        panel.add(lblPrecio, panelGbc);

        txtApellido = new JTextField();
        txtApellido.setPreferredSize(TEXT_FIELD_SIZE);
        panelGbc.gridx = 1;
        panelGbc.gridy = 1;
        panel.add(txtApellido, panelGbc);

        JLabel lblFecha = new JLabel("Fecha de Nacimiento (dd/MM/yyyy):");
        lblFecha.setPreferredSize(LABEL_SIZE);
        panelGbc.gridx = 0;
        panelGbc.gridy = 2;
        panel.add(lblFecha, panelGbc);

        txtNacimiento = new JTextField();
        txtNacimiento.setPreferredSize(TEXT_FIELD_SIZE);
        panelGbc.gridx = 1;
        panelGbc.gridy = 2;
        panel.add(txtNacimiento, panelGbc);

        JLabel lblTelefono = new JLabel("Telefono:");
        lblTelefono.setPreferredSize(LABEL_SIZE);
        panelGbc.gridx = 0;
        panelGbc.gridy = 3;
        panel.add(lblTelefono, panelGbc);

        txtTelefono = new JTextField();
        txtTelefono.setPreferredSize(TEXT_FIELD_SIZE);
        panelGbc.gridx = 1;
        panelGbc.gridy = 3;
        panel.add(txtTelefono, panelGbc);

        JLabel lblEmail = new JLabel("Correo electrónico:");
        lblEmail.setPreferredSize(LABEL_SIZE);
        panelGbc.gridx = 0;
        panelGbc.gridy = 4;
        panel.add(lblEmail, panelGbc);

        txtEmail = new JTextField();
        txtEmail.setPreferredSize(TEXT_FIELD_SIZE);
        panelGbc.gridx = 1;
        panelGbc.gridy = 4;
        panel.add(txtEmail, panelGbc);

        JLabel lblCP = new JLabel("Código Postal:");
        lblCP.setPreferredSize(LABEL_SIZE);
        panelGbc.gridx = 0;
        panelGbc.gridy = 5;
        panel.add(lblCP, panelGbc);

        txtCP = new JTextField();
        txtCP.setPreferredSize(TEXT_FIELD_SIZE);
        panelGbc.gridx = 1;
        panelGbc.gridy = 5;
        panel.add(txtCP, panelGbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panelPrincipal.add(panel, gbc);

        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        btnGuardar = new JButton("Guardar");
        panelPrincipal.add(btnGuardar, gbc);

        add(panelPrincipal);
    }

    public void guardarListener(ActionListener listener) {
        btnGuardar.addActionListener(listener);
    }

    public String getNombre() {
        return txtNombre.getText().trim();
    }
    public String getApellido() {
        return txtApellido.getText().trim();
    }
    public String getNacimiento() {return txtNacimiento.getText().trim();}
    public String getTelefono() {return txtTelefono.getText().trim();}
    public String getEmail() {return txtEmail.getText().trim();}
    public String getCP() {return txtCP.getText().trim();}

    public void limpiarCampos(){
        txtNombre.setText("");
        txtApellido.setText("");
        txtNacimiento.setText("");
        txtTelefono.setText("");
        txtEmail.setText("");
        txtCP.setText("");
    }

    public void mostrarMensaje(String message) {
        JOptionPane.showMessageDialog(this, message);
    }
}
