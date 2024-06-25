package Vista.tickets;

import Vista.JavaEscritorio;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class PanelMostrarTicket extends JPanel {
    private DefaultTableModel modeloTabla;
    private JTable tablaServiciosRealizados;

    public PanelMostrarTicket(JavaEscritorio vista) {
        setLayout(new BorderLayout());
        inicializarComponentes();
    }

    private void inicializarComponentes() {
        String[] columnas = {"N-Ticket", "Servicio" , "Cantidad", "PrecioIVA", "Cliente"};
        modeloTabla = new DefaultTableModel(columnas, 0);
        tablaServiciosRealizados = new JTable(modeloTabla);

        add(new JScrollPane(tablaServiciosRealizados), BorderLayout.CENTER);
    }

    public DefaultTableModel getModeloTabla() {
        return modeloTabla;
    }

    public void setModeloTabla(DefaultTableModel modeloTabla) {
        this.modeloTabla = modeloTabla;
    }

    public JTable getTablaServiciosRealizados() {
        return tablaServiciosRealizados;
    }

    public void setTablaServiciosRealizados(JTable tablaServiciosRealizados) {
        this.tablaServiciosRealizados = tablaServiciosRealizados;
    }
}
