package Vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class PanelMostrarCliente extends JPanel {
    private DefaultTableModel modeloTabla;
    private JTable tablaCliente;
    private JButton btnAgregar, btnEliminar;

    public PanelMostrarCliente(JavaEscritorio vista) {
        setLayout(new BorderLayout());
        inicializarComponentes();
    }

    private void inicializarComponentes() {
        String[] columnas = {"Nombre", "N*Telefono" , "Correo"};
        modeloTabla = new DefaultTableModel(columnas, 0);
        tablaCliente = new JTable(modeloTabla);

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER));
        btnAgregar = new JButton("Anadir Cliente");
        btnEliminar = new JButton("Eliminar Cliente");
        panelBotones.add(btnAgregar);
        panelBotones.add(btnEliminar);

        add(new JScrollPane(tablaCliente), BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);
    }

    public DefaultTableModel getModeloTabla() {
        return modeloTabla;
    }

    public JTable getTablaCliente() {
        return tablaCliente;
    }

    public JButton getBtnAgregar() {
        return btnAgregar;
    }

    public JButton getBtnEliminar() {
        return btnEliminar;
    }
}
