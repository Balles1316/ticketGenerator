package Vista.tickets;

import Modelo.Ticket;
import Vista.JavaEscritorio;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PanelGenerarTicket extends JPanel {
    private JTextField txtNumeroTicket, txtCantidad, txtPrecioConIVA;
    private JComboBox<String> comboServicios;
    private JButton btnImprimir;
    private JavaEscritorio vista;
    public List<Ticket> tickets;
    public static int nTicket ;

    public PanelGenerarTicket(JavaEscritorio vista) {
        this.vista = vista;
        inicializarComponentes();
    }

    private void inicializarComponentes() {
        setLayout(new GridLayout(5, 2, 10, 10));
        setBorder(BorderFactory.createTitledBorder("Datos del Ticket"));

        txtNumeroTicket = new JTextField(String.valueOf(nTicket));
        txtCantidad = new JTextField("1");
        txtPrecioConIVA = new JTextField();
        comboServicios = new JComboBox<>();
        btnImprimir = new JButton("Imprimir");

        add(new JLabel("Numero de Ticket:"));
        add(txtNumeroTicket);
        add(new JLabel("Codigo Servicio:"));
        add(comboServicios);
        add(new JLabel("Cantidad:"));
        add(txtCantidad);
        add(new JLabel("Precio con IVA:"));
        add(txtPrecioConIVA);
        add(new JLabel(""));
        add(btnImprimir);

        comboServicios.addActionListener(e -> {
            if (comboServicios.getSelectedItem() != null) {
                for (int i = 0; i < vista.getModeloTabla().getRowCount(); i++) {
                    if (comboServicios.getSelectedItem().toString().equals(vista.getModeloTabla().getValueAt(i, 0))) {
                        Object precioConIVAObj = vista.getModeloTabla().getValueAt(i, 1);
                        String precioConIVA = precioConIVAObj.toString(); // Convertir a String
                        txtPrecioConIVA.setText(precioConIVA);
                        try {
                            int cantidad = Integer.parseInt(txtCantidad.getText());
                            addServicio(nTicket, comboServicios.getSelectedItem().toString(), Integer.parseInt(txtCantidad.getText()), precioConIVA);
                        } catch (NumberFormatException ex) {
                            JOptionPane.showMessageDialog(this, "Cantidad debe ser un número entero.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                        break;
                    }
                }
            }
        });

    }

    public void addServicio(int nTicket, String nameServicio, int cantidad, String precioConIVA) {
        Ticket ticket = new Ticket(nTicket, nameServicio, cantidad, precioConIVA);
        /*tickets.add(ticket); // Agregar el ticket a la lista*/
    }


    public JButton getBtnImprimir() {
        return btnImprimir;
    }

    public void actualizarComboBoxCodigoServicio(DefaultTableModel modeloTabla) {
        Set<String> codigoServicioSet = new HashSet<>();
        for (int i = 0; i < modeloTabla.getRowCount(); i++) {
            String codigoServicio = (String) modeloTabla.getValueAt(i, 0);
            codigoServicioSet.add(codigoServicio);
        }
        comboServicios.removeAllItems();
        for (String codigoServicio : codigoServicioSet) {
            comboServicios.addItem(codigoServicio);
        }
    }

    public JComboBox<String> getComboServicios() {
        return comboServicios;
    }

    public JTextField getTxtPrecioConIVA() {
        return txtPrecioConIVA;
    }
}