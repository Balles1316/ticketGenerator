package Vista.tickets;

import Vista.JavaEscritorio;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class MostrarTicketView extends JPanel {
    private DefaultTableModel modeloTabla;
    private JTable tablaCliente;

    public MostrarTicketView(JavaEscritorio vista) {
        setLayout(new BorderLayout());
        inicializarComponentes();
    }

    private void inicializarComponentes() {
        String[] columnas = {"N-Ticket", "Servicio" , "Cantidad", "PrecioIVA", "Cliente"};
        modeloTabla = new DefaultTableModel(columnas, 0);
        tablaCliente = new JTable(modeloTabla);

        add(new JScrollPane(tablaCliente), BorderLayout.CENTER);
    }

    public DefaultTableModel getModeloTabla() {
        return modeloTabla;
    }

    public JTable getTablaCliente() {
        return tablaCliente;
    }

}
