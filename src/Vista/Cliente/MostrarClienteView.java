package Vista.Cliente;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

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
        tablaCliente.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        add(new JScrollPane(tablaCliente), BorderLayout.CENTER);
    }

    public DefaultTableModel getModeloTabla() {
        return modeloTabla;
    }

    public JTable getTablaClientes() {
        return tablaCliente;
    }

    public void mostrarMensaje(String message) {
        JOptionPane.showMessageDialog(this, message);
    }
}
