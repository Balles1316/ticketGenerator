package Vista.Ticket;

import Objeto.Ticket;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GenerarTicketView extends JPanel {
    private JTextField txtNumeroTicket, txtCantidad, txtPrecioConIVA, txtCliente, txtClienteEncontrado;
    private JComboBox<String> comboServicios;
    private JButton btnImprimir, btnBuscar;
    private JRadioButton jRadiometalico, jRadioVisa ;
    public List<Ticket> tickets;
    public static int nTicket;

    public GenerarTicketView() {
        setLayout(new BorderLayout());
        inicializarComponentes();
    }

    private void inicializarComponentes() {
        JPanel panelFormulario = new JPanel(new GridLayout(7, 2, 10, 10));
        panelFormulario.setBorder(BorderFactory.createTitledBorder("Datos del Cliente"));

        txtNumeroTicket = new JTextField();
        comboServicios = new JComboBox<>();
        txtCantidad = new JTextField("1");
        txtPrecioConIVA = new JTextField();
        txtCliente = new JTextField();
        btnBuscar = new JButton("Buscar");
        txtClienteEncontrado = new JTextField();
        btnImprimir = new JButton("Imprimir");
        jRadiometalico = new JRadioButton("Metalico");
        jRadioVisa = new JRadioButton("Tarjeta Crédito/Débito ");

        // Set jRadioVisa as the default selected option
        jRadioVisa.setSelected(true);

        ButtonGroup bGroup = new ButtonGroup();
        bGroup.add(jRadiometalico);
        bGroup.add(jRadioVisa);

        panelFormulario.add(new JLabel("Numero Ticket:"));
        panelFormulario.add(txtNumeroTicket);
        panelFormulario.add(new JLabel("Servicio:"));
        panelFormulario.add(comboServicios);
        panelFormulario.add(new JLabel("Cantidad:"));
        panelFormulario.add(txtCantidad);
        panelFormulario.add(new JLabel("Precio ConIVA:"));
        panelFormulario.add(txtPrecioConIVA);
        panelFormulario.add(new JLabel("Cliente:"));
        JPanel clientePanel = new JPanel(new BorderLayout());
        clientePanel.add(txtCliente, BorderLayout.CENTER);
        clientePanel.add(btnBuscar, BorderLayout.EAST);
        panelFormulario.add(clientePanel);
        panelFormulario.add(new JLabel("Cliente Encontrado:"));
        panelFormulario.add(txtClienteEncontrado);
        panelFormulario.add(new JLabel("Metodo de Pago"));
        JPanel metodoPanel = new JPanel(new FlowLayout());
        metodoPanel.add(jRadiometalico);
        metodoPanel.add(jRadioVisa);
        panelFormulario.add(metodoPanel);

        add(panelFormulario, BorderLayout.CENTER);

        JPanel panelBoton = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelBoton.add(btnImprimir);
        add(panelBoton, BorderLayout.SOUTH);

        /*comboServicios.addActionListener(e -> {
            if (comboServicios.getSelectedItem() != null) {
                for (int i = 0; i < vista.getModeloTabla().getRowCount(); i++) {
                    if (comboServicios.getSelectedItem().toString().equals(vista.getModeloTabla().getValueAt(i, 0))) {
                        Object precioConIVAObj = vista.getModeloTabla().getValueAt(i, 1);
                        String precioConIVA = precioConIVAObj.toString(); // Convertir a String
                        txtPrecioConIVA.setText(precioConIVA);
                        try {
                            int cantidad = Integer.parseInt(txtCantidad.getText());
                            //addServicio(nTicket, comboServicios.getSelectedItem().toString(), cantidad, precioConIVA);
                        } catch (NumberFormatException ex) {
                            JOptionPane.showMessageDialog(this, "Cantidad debe ser un número entero.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                        break;
                    }
                }
            }
        });*/
    }

/*    public void addServicio(int nTicket, String nameServicio, int cantidad, String precioConIVA) {
        Ticket ticket = new Ticket(nTicket, nameServicio, cantidad, precioConIVA);
        // tickets.add(ticket); // Agregar el ticket a la lista
    }*/

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
