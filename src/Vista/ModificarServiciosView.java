package Vista;

import Database.Consulta;
import Database.Insertar;
import Database.Modificar;
import Modelo.Servicio;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ModificarServiciosView extends JPanel{
    private JavaEscritorio vista;

    public ModificarServiciosView(JavaEscritorio vista) {
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

        JLabel lblTitulo = new JLabel("Modificar Servicios");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 24));

        panelPrincipal.add(lblTitulo, gbc);

        // SIGUIENTE LINEA
        gbc.gridy = 1;
        JPanel panelPregunta = new JPanel(new GridBagLayout());
        GridBagConstraints GbcPregunta = new GridBagConstraints();
        GbcPregunta.insets = new Insets(10, 10, 10, 10);

        GbcPregunta.gridx = 0;
        GbcPregunta.gridy = 0;

        JLabel lblPregunta = new JLabel("Nombre:");
        lblPregunta.setPreferredSize(new Dimension(70, 25));
        panelPregunta.add(lblPregunta, GbcPregunta);

        GbcPregunta.gridx = 1;
        GbcPregunta.gridy = 0;
        JTextField tfPregunta = new JTextField();
        tfPregunta.setPreferredSize(new Dimension(200, 25));
        panelPregunta.add(tfPregunta, GbcPregunta);

        GbcPregunta.gridx = 2;
        GbcPregunta.gridy = 0;
        JButton btnBuscar = new JButton("Buscar");
        panelPregunta.add(btnBuscar, GbcPregunta);

        panelPrincipal.add(panelPregunta, gbc);

        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JPanel panelDatos = new JPanel(new GridBagLayout());
                GridBagConstraints gbcDatos = new GridBagConstraints();
                gbcDatos.insets = new Insets(10, 10, 10, 10);

                gbcDatos.gridx = 0;
                gbcDatos.gridy = 0;
                JLabel lblNombre = new JLabel("Nombre:");
                lblNombre.setPreferredSize(new Dimension(70, 25));
                panelDatos.add(lblNombre, gbcDatos);

                gbcDatos.gridx = 1;
                gbcDatos.gridy = 0;
                JTextField txtNombre = new JTextField();
                txtNombre.setPreferredSize(new Dimension(200, 25));
                panelDatos.add(txtNombre, gbcDatos);

                gbcDatos.gridx = 0;
                gbcDatos.gridy = 1;
                JLabel lblPrecio = new JLabel("Precio:");
                lblPrecio.setPreferredSize(new Dimension(70, 25));
                panelDatos.add(lblPrecio, gbcDatos);

                gbcDatos.gridx = 1;
                gbcDatos.gridy = 1;
                JTextField txtPrecio = new JTextField();
                txtPrecio.setPreferredSize(new Dimension(200, 25));
                panelDatos.add(txtPrecio, gbcDatos);

                // SIGUIENTE LINEA
                gbc.gridy = 2; // Posición después del panel de pregunta
                gbc.anchor = GridBagConstraints.CENTER;
                panelPrincipal.add(panelDatos, gbc);

                Consulta consulta = new Consulta();
                consulta.consultarServiciosPorNombre(tfPregunta.getText().trim());

                List<Servicio> serviciosList = consulta.getServiciosList();
                for (Servicio servicio : serviciosList) {
                    txtNombre.setText(servicio.getNombre());
                    txtPrecio.setText(String.valueOf(servicio.getPrecio()));
                }

                // SIGUIENTE LINEA
                gbc.gridy = 3;
                gbc.anchor = GridBagConstraints.CENTER;
                JButton btnGuardar = new JButton("Modificar");
                panelPrincipal.add(btnGuardar, gbc);

                btnGuardar.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Modificar modificar = new Modificar();
                        modificar.modificarServicio(txtNombre.getText().trim(), Double.parseDouble(txtPrecio.getText().trim()), tfPregunta.getText().trim());

                        JOptionPane.showMessageDialog(vista, "Servicio modificado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                        txtNombre.setText("");
                        txtPrecio.setText("");
                        tfPregunta.setText("");
                    }
                });

                // Actualizar la interfaz gráfica
                revalidate();
                repaint();
            }
        });


        add(panelPrincipal);
    }
}
