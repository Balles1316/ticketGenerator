package Vista.Servicio;

import Vista.JavaEscritorio;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class MostrarServiciosView extends JPanel {
    private DefaultTableModel modeloTabla;
    private JTable tablaPrecios;

    public MostrarServiciosView(JavaEscritorio vista) {
        setLayout(new BorderLayout());
        inicializarComponentes();
    }

    private void inicializarComponentes() {
        String[] columnas = {"Producto", "Precio"};
        modeloTabla = new DefaultTableModel(columnas, 0);
        tablaPrecios = new JTable(modeloTabla);
        add(new JScrollPane(tablaPrecios), BorderLayout.CENTER);
    }

    public DefaultTableModel getModeloTabla() {
        return modeloTabla;
    }

    public JTable getTablaPrecios() {
        return tablaPrecios;
    }
}
