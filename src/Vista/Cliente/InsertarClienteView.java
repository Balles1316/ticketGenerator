package Vista.Cliente;

/*
import Database.Remoto.Insertar;
*/
import Modelo.Cliente;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutionException;

public class InsertarClienteView extends JPanel {

    private JTextField txtNombre, txtApellido, txtFecha, txtTelefono, txtEmail, txtCP;

    public InsertarClienteView() {
        setLayout(new BorderLayout());
        inicializarComponentes();
    }

    private void inicializarComponentes() {
        JPanel panelFormulario = new JPanel(new GridLayout(6, 2, 10, 10));
        panelFormulario.setBorder(BorderFactory.createTitledBorder("Datos del Cliente"));

        txtNombre = new JTextField();
        txtApellido = new JTextField();
        txtFecha = new JTextField();
        txtTelefono = new JTextField();
        txtEmail = new JTextField();
        txtCP = new JTextField();
        JButton btnGuardar = new JButton("Guardar");

        panelFormulario.add(new JLabel("Nombre:"));
        panelFormulario.add(txtNombre);
        panelFormulario.add(new JLabel("Apellido:"));
        panelFormulario.add(txtApellido);
        panelFormulario.add(new JLabel("Dirección:"));
        panelFormulario.add(txtFecha);
        panelFormulario.add(new JLabel("Teléfono:"));
        panelFormulario.add(txtTelefono);
        panelFormulario.add(new JLabel("Correo Electrónico:"));
        panelFormulario.add(txtEmail);
        panelFormulario.add(new JLabel("Código Postal:"));
        panelFormulario.add(txtCP);
        add(panelFormulario, BorderLayout.CENTER);

        JPanel panelBoton = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelBoton.add(btnGuardar);
        add(panelBoton, BorderLayout.SOUTH);

        btnGuardar.addActionListener(e -> {
            String nombre = txtNombre.getText().trim();
            String apellido = txtApellido.getText().trim();

            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date fecha;
            try {
                fecha = dateFormat.parse(txtFecha.getText().trim());
            } catch (ParseException ex) {
                System.err.println("Error al convertir la fecha: " + ex.getMessage());
                return;
            }

            String telefono = txtTelefono.getText().trim();
            String email = txtEmail.getText().trim();
            String cp = txtCP.getText().trim();

/*
            Insertar insertar = new Insertar();
            Cliente nuevoCliente = new Cliente(nombre, apellido, fecha, telefono, email, cp);
            try {
                insertar.insertarServicio(nuevoCliente);
            } catch (IOException | ExecutionException | InterruptedException ex) {
                throw new RuntimeException(ex);
            }
*/
            limpiarCampos();
        });
    }

    private void limpiarCampos() {
        txtNombre.setText("");
        txtApellido.setText("");
        txtFecha.setText("");
        txtTelefono.setText("");
        txtEmail.setText("");
        txtCP.setText("");

    }
}
