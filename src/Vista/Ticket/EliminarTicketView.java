package Vista.Ticket;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;

public class EliminarTicketView extends JPanel {
    private JTextField tfPregunta;
    private JTextField txtNombre;
    private JTextField txtPrecio;
    private JButton btnGuardar;
    private JButton btnEliminar;
    private JPanel panelDatos;
    private DefaultTableModel modeloTabla;
    private JTable tablaPrecios;
    private JScrollPane scrollPane;

    public EliminarTicketView() {
        setLayout(new BorderLayout());
        initComponents();
    }

    private void initComponents() {
        JPanel panelPrincipal = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // PRIMERA LINEA
        gbc.insets = new Insets(10, 10, 10, 10); // Márgenes
        gbc.gridx = 0;
        gbc.gridy = 0;

        JLabel lblTitulo = new JLabel("Eliminar Servicios");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 24));
        panelPrincipal.add(lblTitulo, gbc);

        // SIGUIENTE LINEA
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.BOTH;
        JPanel panelPregunta = new JPanel(new GridBagLayout());
        panelPregunta.setBorder(BorderFactory.createTitledBorder("Servicio a buscar"));
        GridBagConstraints gbcPregunta = new GridBagConstraints();
        gbcPregunta.insets = new Insets(10, 10, 10, 10);

        gbcPregunta.gridx = 0;
        gbcPregunta.gridy = 0;
        tfPregunta = new JTextField();
        tfPregunta.setPreferredSize(new Dimension(200, 25));
        panelPregunta.add(tfPregunta, gbcPregunta);

        gbcPregunta.gridx = 1;
        gbcPregunta.gridy = 0;
        btnGuardar = new JButton("Buscar");
        panelPregunta.add(btnGuardar, gbcPregunta);

        panelPrincipal.add(panelPregunta, gbc);

        // SIGUIENTE LINEA
        gbc.gridy = 2;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;

        String[] columnas = {"Producto", "Precio"};
        modeloTabla = new DefaultTableModel(columnas, 0);
        tablaPrecios = new JTable(modeloTabla);
        scrollPane = new JScrollPane(tablaPrecios);
        scrollPane.setVisible(false);
        panelPrincipal.add(scrollPane, gbc);

        // SIGUIENTE LINEA
        gbc.gridy = 3;
        panelDatos = new JPanel(new GridBagLayout());
        panelDatos.setVisible(false);
        GridBagConstraints gbcDatos = new GridBagConstraints();
        gbcDatos.insets = new Insets(10, 10, 10, 10);
        gbcDatos.gridy = 2;
        gbcDatos.anchor = GridBagConstraints.CENTER;
        btnEliminar = new JButton("Eliminar");
        btnEliminar.setVisible(false);
        panelDatos.add(btnEliminar, gbcDatos);

        panelPrincipal.add(panelDatos, gbc);

        add(panelPrincipal);
    }

    public void buscarListener(ActionListener listener) {
        btnGuardar.addActionListener(listener);
    }

    public void eliminarListener(ActionListener listener) {
        btnEliminar.addActionListener(listener);
    }

    public JScrollPane getScrollPane() {
        return scrollPane;
    }

    public DefaultTableModel getModeloTabla() {
        return modeloTabla;
    }

    public JTable getTablaPrecios() {
        return tablaPrecios;
    }

    public JPanel getPanelDatos() {
        return panelDatos;
    }

    public JButton getBtnEliminar() {
        return btnEliminar;
    }

    public String getNombreABuscar() {
        return tfPregunta.getText().trim();
    }

    public String getNombre() {
        return txtNombre.getText().trim();
    }

    public String getPrecio() {
        return txtPrecio.getText().trim();
    }

    public void mostrarMensaje(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    public void confirmarEliminacion(String nombreServicio, Runnable eliminacionConfirmada) {
        if (nombreServicio == null || nombreServicio.isEmpty()) {
            mostrarMensaje("Por favor, selecciona un servicio para eliminar.");
        } else {
            int opcion = JOptionPane.showConfirmDialog(
                    this, "¿Estás seguro de que quieres eliminar este servicio?", "Confirmar eliminación",
                    JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

            if (opcion == JOptionPane.YES_OPTION) {
                eliminacionConfirmada.run();
            }
        }
    }
}