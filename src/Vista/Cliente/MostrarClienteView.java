package Vista.Cliente;

/*
import Database.Remoto.Consulta;
*/

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MostrarClienteView extends JPanel {
    private DefaultTableModel modeloTabla;
    private JTable tablaCliente;

    public MostrarClienteView() {
        setLayout(new BorderLayout());
        inicializarComponentes();
    }

    private void inicializarComponentes() {
        String[] columnas = {"Nombre", "Apellido", "Fecha Nacimiento", "Teléfono", "Correo Electrónico", "Código postal"};
        modeloTabla = new DefaultTableModel(columnas, 0);
        tablaCliente = new JTable(modeloTabla);

/*
        try {
            Consulta consulta = new Consulta();
            consulta.consultarClientes();

            if (tablaCliente == null || tablaCliente.getModel() != modeloTabla)
                tablaCliente.setModel(modeloTabla);

            tablaCliente.setEnabled(true);
            tablaCliente.setDefaultEditor(Object.class, new DefaultCellEditor(new JTextField()));
            modeloTabla.setRowCount(0);

            List<Cliente> clientesList = consulta.getServiciosList();
            for (Cliente cliente : clientesList) {
                modeloTabla.addRow(new Object[]{cliente.getNombre(), cliente.getApellido(), formatDate(cliente.getfNacimiento()), cliente.getTelefono(), cliente.getEmail(), cliente.getCp()});
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al cargar los clientes.", "Error", JOptionPane.ERROR_MESSAGE);
        }
*/

        add(new JScrollPane(tablaCliente), BorderLayout.CENTER);
    }


    private String formatDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(date);
    }
}
