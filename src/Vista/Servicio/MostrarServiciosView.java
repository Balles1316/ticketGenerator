package Vista.Servicio;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class MostrarServiciosView extends JPanel {
    private DefaultTableModel modeloTabla;
    private JTable tablaServicios;

    public MostrarServiciosView() {
        setLayout(new BorderLayout());
        inicializarComponentes();
    }

    private void inicializarComponentes() {
        String[] columnas = {"Producto", "Precio"};
        modeloTabla = new DefaultTableModel(columnas, 0);
        tablaServicios = new JTable(modeloTabla);
        tablaServicios.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        add(new JScrollPane(tablaServicios), BorderLayout.CENTER);
    }

    public DefaultTableModel getModeloTabla() {
        return modeloTabla;
    }

    public JTable getTablaPrecios() {
        return tablaServicios;
    }


    public void mostrarMensaje(String message) {
        JOptionPane.showMessageDialog(this, message);
    }
}
