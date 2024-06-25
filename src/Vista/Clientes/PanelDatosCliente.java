package Vista.Clientes;

import Vista.JavaEscritorio;

import javax.swing.*;
import java.awt.*;

public class PanelDatosCliente extends JPanel {

    private JTextField txtNombre;
    private JTextField txtApellido;
    private JTextField txtDireccion;
    private JTextField txtTelefono;
    private JTextField txtEmail;
    private JButton btnGuardar;

    public PanelDatosCliente(JavaEscritorio vista) {
        setLayout(new BorderLayout());
        inicializarComponentes();
    }

    private void inicializarComponentes() {
        JPanel panelFormulario = new JPanel(new GridLayout(6, 2, 10, 10));
        panelFormulario.setBorder(BorderFactory.createTitledBorder("Datos del Cliente"));

        txtNombre = new JTextField();
        txtApellido = new JTextField();
        txtDireccion = new JTextField();
        txtTelefono = new JTextField();
        txtEmail = new JTextField();
        btnGuardar = new JButton("Guardar");

        panelFormulario.add(new JLabel("Nombre:"));
        panelFormulario.add(txtNombre);
        panelFormulario.add(new JLabel("Apellido:"));
        panelFormulario.add(txtApellido);
        panelFormulario.add(new JLabel("Dirección:"));
        panelFormulario.add(txtDireccion);
        panelFormulario.add(new JLabel("Teléfono:"));
        panelFormulario.add(txtTelefono);
        panelFormulario.add(new JLabel("Correo Electrónico:"));
        panelFormulario.add(txtEmail);

        // Espacio vacío para mantener el diseño
        panelFormulario.add(new JLabel(""));
        panelFormulario.add(btnGuardar);

        add(panelFormulario, BorderLayout.CENTER);

        // Agregar ActionListener al botón guardar para manejar la acción de guardar
        btnGuardar.addActionListener(e -> guardarDatosCliente());
    }

    private void guardarDatosCliente() {
        String nombre = txtNombre.getText();
        String apellido = txtApellido.getText();
        String direccion = txtDireccion.getText();
        String telefono = txtTelefono.getText();
        String email = txtEmail.getText();

        // Aquí puedes agregar la lógica para guardar los datos del cliente, por ejemplo, en una base de datos o un archivo
        System.out.println("Datos del Cliente Guardados:");
        System.out.println("Nombre: " + nombre);
        System.out.println("Apellido: " + apellido);
        System.out.println("Dirección: " + direccion);
        System.out.println("Teléfono: " + telefono);
        System.out.println("Correo Electrónico: " + email);
    }
}
