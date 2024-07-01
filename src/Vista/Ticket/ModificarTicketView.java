package Vista.Ticket;

import Database.Clientes.Consulta;
import Database.Clientes.Modificar;
import Objeto.Servicio;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ModificarTicketView extends JPanel {
    private JTextField tfPregunta;
    private JTextField txtNombre;
    private JTextField txtPrecio;
    private JButton btnGuardar;
    private JPanel panelDatos;

    public ModificarTicketView() {
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

        JLabel lblTitulo = new JLabel("Modificar Ticket");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 24));

        panelPrincipal.add(lblTitulo, gbc);

        // SIGUIENTE LINEA
        gbc.gridy = 1;
        JPanel panelPregunta = new JPanel(new GridBagLayout());
        GridBagConstraints gbcPregunta = new GridBagConstraints();
        gbcPregunta.insets = new Insets(10, 10, 10, 10);

        gbcPregunta.gridx = 0;
        gbcPregunta.gridy = 0;

        JLabel lblPregunta = new JLabel("Nombre a buscar:");
        lblPregunta.setPreferredSize(new Dimension(110, 25));
        panelPregunta.add(lblPregunta, gbcPregunta);

        gbcPregunta.gridx = 1;
        gbcPregunta.gridy = 0;
        tfPregunta = new JTextField();
        tfPregunta.setPreferredSize(new Dimension(200, 25));
        panelPregunta.add(tfPregunta, gbcPregunta);

        gbcPregunta.gridx = 2;
        gbcPregunta.gridy = 0;
        JButton btnBuscar = new JButton("Buscar");
        panelPregunta.add(btnBuscar, gbcPregunta);

        panelPrincipal.add(panelPregunta, gbc);

        panelDatos = new JPanel(new GridBagLayout());
        GridBagConstraints gbcDatos = new GridBagConstraints();
        gbcDatos.insets = new Insets(10, 10, 10, 10);

        gbcDatos.gridx = 0;
        gbcDatos.gridy = 0;
        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setPreferredSize(new Dimension(70, 25));
        panelDatos.add(lblNombre, gbcDatos);

        gbcDatos.gridx = 1;
        gbcDatos.gridy = 0;
        txtNombre = new JTextField();
        txtNombre.setPreferredSize(new Dimension(200, 25));
        panelDatos.add(txtNombre, gbcDatos);

        gbcDatos.gridx = 0;
        gbcDatos.gridy = 1;
        JLabel lblPrecio = new JLabel("Precio:");
        lblPrecio.setPreferredSize(new Dimension(70, 25));
        panelDatos.add(lblPrecio, gbcDatos);

        gbcDatos.gridx = 1;
        gbcDatos.gridy = 1;
        txtPrecio = new JTextField();
        txtPrecio.setPreferredSize(new Dimension(200, 25));
        panelDatos.add(txtPrecio, gbcDatos);

        gbc.gridy = 2; // Posición después del panel de pregunta
        gbc.anchor = GridBagConstraints.CENTER;
        panelPrincipal.add(panelDatos, gbc);

        // SIGUIENTE LINEA
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.CENTER;
        btnGuardar = new JButton("Modificar");
        panelPrincipal.add(btnGuardar, gbc);

        add(panelPrincipal);
    }
}