package Vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class PanelEditarPrecios extends JPanel {
    private DefaultTableModel modeloTabla;
    private JTable tablaPrecios;
    private JButton btnAgregar, btnEliminar;

    public PanelEditarPrecios(JavaEscritorio vista) {
        setLayout(new BorderLayout());
        inicializarComponentes();
    }

    private void inicializarComponentes() {
        String[] columnas = {"Producto", "Precio"};
        modeloTabla = new DefaultTableModel(columnas, 0);
        tablaPrecios = new JTable(modeloTabla);

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER));
        btnAgregar = new JButton("Anadir Producto");
        btnEliminar = new JButton("Eliminar Producto");
        panelBotones.add(btnAgregar);
        panelBotones.add(btnEliminar);

        add(new JScrollPane(tablaPrecios), BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);
    }

    public DefaultTableModel getModeloTabla() {
        return modeloTabla;
    }

    public JTable getTablaPrecios() {
        return tablaPrecios;
    }

    public JButton getBtnAgregar() {
        return btnAgregar;
    }

    public JButton getBtnEliminar() {
        return btnEliminar;
    }
}
