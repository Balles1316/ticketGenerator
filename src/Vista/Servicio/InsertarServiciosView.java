package Vista.Servicio;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class InsertarServiciosView extends JPanel {
    private JTextField txtNombre;
    private JTextField txtPrecio;
    private JButton btnGuardar;

    private static final int INSETS_TOP = 10, INSETS_BOTTOM = 10, INSETS_LEFT = 10, INSETS_RIGHT = 10;
    private static final Dimension LABEL_SIZE = new Dimension(70, 25), TEXT_FIELD_SIZE = new Dimension(200, 25);

    public InsertarServiciosView() {
        setLayout(new BorderLayout());
        initComponents();
    }

    private void initComponents() {
        JPanel panelPrincipal = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(INSETS_TOP, INSETS_LEFT, INSETS_BOTTOM, INSETS_RIGHT);

        JLabel lblTitulo = new JLabel("Insertar Servicios");
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

        JLabel lblPrecio = new JLabel("Precio:");
        lblPrecio.setPreferredSize(LABEL_SIZE);
        panelGbc.gridx = 0;
        panelGbc.gridy = 1;
        panel.add(lblPrecio, panelGbc);

        txtPrecio = new JTextField();
        txtPrecio.setPreferredSize(TEXT_FIELD_SIZE);
        panelGbc.gridx = 1;
        panelGbc.gridy = 1;
        panel.add(txtPrecio, panelGbc);

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

    public String getPrecio() {
        return txtPrecio.getText().trim();
    }

    public void limpiarCampos(){
        txtNombre.setText("");
        txtPrecio.setText("");
    }

    public void mostrarMensaje(String message) {
        JOptionPane.showMessageDialog(this, message);
    }
}