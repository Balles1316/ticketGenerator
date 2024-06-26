package Vista.tickets;

import Database.Clientes.Consulta;
import Database.Clientes.Modificar;
import Modelo.Servicio;
import Vista.JavaEscritorio;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ModificarTicketView extends JPanel {
    private JavaEscritorio vista;
    private JTextField tfPregunta;
    private JTextField txtNombre;
    private JTextField txtPrecio;
    private JButton btnGuardar;
    private JPanel panelDatos;

    public ModificarTicketView(JavaEscritorio vista) {
        this.vista = vista;
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

        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarServicio();
            }
        });

        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modificarServicio();
            }
        });

        add(panelPrincipal);
    }

    private void buscarServicio() {
        String nombreBusqueda = tfPregunta.getText().trim();
        if (nombreBusqueda.isEmpty()) {
            JOptionPane.showMessageDialog(vista, "Por favor, ingresa un nombre para buscar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Consulta consulta = new Consulta();
        consulta.consultarServiciosPorNombre(nombreBusqueda);

        List<Servicio> serviciosList = consulta.getServiciosList();
        if (!serviciosList.isEmpty()) {
            Servicio servicio = serviciosList.get(0); // Tomamos el primer servicio encontrado
            txtNombre.setText(servicio.getNombre());
            txtPrecio.setText(String.valueOf(servicio.getPrecio()));
        } else {
            JOptionPane.showMessageDialog(vista, "No se encontró ningún servicio con ese nombre.", "Información", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void modificarServicio() {
        String nuevoNombre = txtNombre.getText().trim();
        String nuevoPrecio = txtPrecio.getText().trim();
        String nombreBusqueda = tfPregunta.getText().trim();

        if (nuevoNombre.isEmpty() || nuevoPrecio.isEmpty()) {
            JOptionPane.showMessageDialog(vista, "Por favor, completa todos los campos.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            double precio = Double.parseDouble(nuevoPrecio);
            Modificar modificar = new Modificar();
            modificar.modificarServicio(nuevoNombre, precio, nombreBusqueda);
            JOptionPane.showMessageDialog(vista, "Servicio modificado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            txtNombre.setText("");
            txtPrecio.setText("");
            tfPregunta.setText("");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(vista, "El precio debe ser un número.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}