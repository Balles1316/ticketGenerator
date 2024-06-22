package Vista.Servicio;

import Database.Insertar;
import Vista.JavaEscritorio;

import javax.swing.*;
import java.awt.*;

public class InsertarServiciosView extends JPanel {
    private JTextField txtNombre;
    private JTextField txtPrecio;
    private JavaEscritorio vista;
    private Insertar insertar;

    public InsertarServiciosView(JavaEscritorio vista) {
        setLayout(new BorderLayout());
        initComponents();
        this.vista = vista;
    }

    private void initComponents() {
        JPanel panelPrincipal = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // PRIMERA LINEA
        gbc.insets = new Insets(10, 10, 10, 10); // Márgenes
        gbc.gridx = 0;
        gbc.gridy = 0;

        JLabel lblTitulo = new JLabel("Insertar Servicios");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 24));

        panelPrincipal.add(lblTitulo, gbc);

        // SIGUIENTE LINEA
        gbc.gridy = 1;

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints panelGbc = new GridBagConstraints();

        gbc.insets = new Insets(10, 10, 10, 10); // Márgenes

        panelGbc.gridx = 0;
        panelGbc.gridy = 0;
        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setPreferredSize(new Dimension(70, 25));
        panel.add(lblNombre, panelGbc);

        panelGbc.gridx = 1;
        panelGbc.gridy = 0;
        txtNombre = new JTextField();
        txtNombre.setPreferredSize(new Dimension(200, 25));
        panel.add(txtNombre, panelGbc);

        panelGbc.gridx = 0;
        panelGbc.gridy = 1;
        JLabel lblPrecio = new JLabel("Precio:");
        lblPrecio.setPreferredSize(new Dimension(70, 25));
        panel.add(lblPrecio, panelGbc);

        panelGbc.gridx = 1;
        panelGbc.gridy = 1;
        txtPrecio = new JTextField();
        txtPrecio.setPreferredSize(new Dimension(200, 25));
        panel.add(txtPrecio, panelGbc);


        panelPrincipal.add(panel, gbc);

        // SIGUIENTE LINEA
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        JButton btnGuardar = new JButton("Guardar");

        panelPrincipal.add(btnGuardar, gbc);
        add(panelPrincipal);

        btnGuardar.addActionListener(_ -> {
            String nombre = txtNombre.getText().trim();
            String precioStr = txtPrecio.getText().trim();

            if (!nombre.isEmpty()) {
                if (!precioStr.isEmpty()) {
                    try {
                        double precio = Double.parseDouble(precioStr);
                        if (precio > 0.0) {
                            insertar = new Insertar(nombre, precio);
                            insertar.insertarServicio();
                            JOptionPane.showMessageDialog(vista, "Servicio guardado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                            txtNombre.setText("");
                            txtPrecio.setText("");
                        } else {
                            JOptionPane.showMessageDialog(vista, "El precio debe ser mayor que cero.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (NumberFormatException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(vista, "Formato de precio incorrecto.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(vista, "El precio no puede ser vacío.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(vista, "El nombre no puede ser vacío.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}