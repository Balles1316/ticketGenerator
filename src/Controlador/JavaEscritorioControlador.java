package Controlador;

import Vista.JavaEscritorio;

import java.awt.Component;
import java.awt.event.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import javax.swing.DefaultCellEditor;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.swing.DefaultCellEditor;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

//Quiero que cuando le das al boton de implementar las acciones de los b
public class JavaEscritorioControlador extends WindowAdapter {
    private JavaEscritorio vista;
	private DefaultTableModel  modeloTabla ;
	private JTable tablaPrecios ;

    public JavaEscritorioControlador(JavaEscritorio vista) {
        this.vista = vista;
        this.vista.addWindowListener(this);
		modeloTabla = vista.getModeloTabla();
		tablaPrecios = vista.getTable();
		agregarOyentesDeEventos();
    }

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				JavaEscritorio vista = new JavaEscritorio();
				new JavaEscritorioControlador(vista);
				vista.setVisible(true);
			}
		});
	}

	private void agregarOyentesDeEventos() {
		
        vista.getBtnAgregar().addActionListener(new ActionListener() {
			@Override
            public void actionPerformed(ActionEvent e) {
                // A�ade una fila vac�a al modelo de la tabla para que el usuario pueda introducir un nuevo producto y precio
                modeloTabla.addRow(new Object[]{"", ""});
                
                // Asegura que la �ltima celda agregada sea editable
                int fila = modeloTabla.getRowCount() - 1;
                int columna = 0; // Puedes especificar la columna que deseas que sea editable aqu�
                tablaPrecios.editCellAt(fila, columna);
                Component editor = tablaPrecios.getEditorComponent();
                if (editor != null) {
                    editor.requestFocus();
                }
            }
        });

		vista.getBtnEliminar().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Obtiene la fila seleccionada y la elimina del modelo de la tabla
				int filaSeleccionada = tablaPrecios.getSelectedRow();
				if (filaSeleccionada >= 0) {
					modeloTabla.removeRow(filaSeleccionada);
				} else {
					JOptionPane.showMessageDialog(null, "Por favor, selecciona un producto para eliminar.");
				}
				guardarPrecios();
			}
		});
	
        vista.getBtnImprimir().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Aquí va el código que se ejecutará cuando se presione el botón btnPrint
            }
        });
	
    }

	public void guardarPrecios() {
		Properties propiedades = new Properties();

		for (int i = 0; i < modeloTabla.getRowCount(); i++) {
			String producto = (String) modeloTabla.getValueAt(i, 0);
			String precio = (String) modeloTabla.getValueAt(i, 1);
			propiedades.setProperty(producto, precio);
		}

		try {
			propiedades.store(new FileOutputStream("precios.properties"), "Precios de Productos");
			System.out.println("Precios guardados correctamente.");			
		} catch (IOException ex) {
			JOptionPane.showMessageDialog(null, "Error al guardar los precios.", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
    
    public void cargarPrecios() {

        Properties propiedades = new Properties();

        try {
            propiedades.load(new FileInputStream("precios.properties"));

            // Configuramos la tabla si es null o si el modelo es diferente
            if (tablaPrecios == null || tablaPrecios.getModel() != modeloTabla) {
                tablaPrecios.setModel(modeloTabla);
            }

            // Permitimos la edici�n de celdas
            tablaPrecios.setEnabled(true);
            // Configura un editor de celdas para la tabla
            tablaPrecios.setDefaultEditor(Object.class, new DefaultCellEditor(new JTextField()));

            modeloTabla.setRowCount(0); // Limpia la tabla
            for (String key : propiedades.stringPropertyNames()) {
                String precio = propiedades.getProperty(key);
                modeloTabla.addRow(new Object[]{key, precio}); // A�ade los precios a la tabla
            }
            

        } catch (IOException ex) {
            // Manejar la excepci�n si el archivo no existe o hay un error de I/O
            JOptionPane.showMessageDialog(null, "Error al cargar los precios.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

	/** 
	@Override
	public int print(Graphics graphics, PageFormat pageFormat,int pageIndex) 
			  throws PrinterException 
			  {    
			      
			      int r= itemName.size();
			      ImageIcon icon=new ImageIcon("C:\\PARABEUS.jpg"); 
			      int result = NO_SUCH_PAGE;    
			        if (pageIndex == 0) {                    
			        
			            Graphics2D g2d = (Graphics2D) graphics;                    
			            double width = pageFormat.getImageableWidth();                               
			            g2d.translate((int) pageFormat.getImageableX(),(int) pageFormat.getImageableY()); 



			          //  FontMetrics metrics=g2d.getFontMetrics(new Font("Arial",Font.BOLD,7));
			     
			        try{
			            int y=20;
			            int yShift = 10;
			            int headerRectHeight=15;
			           // int headerRectHeighta=40;
			            
			                
			            g2d.setFont(new Font("Monospaced",Font.PLAIN,9));
			            g2d.drawImage(icon.getImage(), 50, 20, 90, 30, rootPane);y+=yShift+30;
			            g2d.drawString("-------------------------------------",10,y);y+=yShift;
			            g2d.drawString("         Parabeus S.L        ",10,y);y+=yShift;
			            g2d.drawString("       Ticket number "+ticketnumber,10,y);y+=yShift;
			            g2d.drawString("       Calle Sagasta n 15    ",10,y);y+=yShift;
			            g2d.drawString("       +34 915 21 48 86      ",10,y);y+=yShift;
			            g2d.drawString("-------------------------------------",10,y);y+=headerRectHeight;

			            g2d.drawString(" Item Name                  Price   ",10,y);y+=yShift;
			            g2d.drawString("-------------------------------------",10,y);y+=headerRectHeight;
			     
			            for(int s=0; s<r; s++)
			            {
			                
			            g2d.drawString(" "+itemName.get(s)+"                            ",10,y);y+=yShift;
			            g2d.drawString("      "+quantity.get(s)+" * "+itemPrice.
			            		 get(s),10,y); g2d.drawString(subtotal.get(s),160,y);y+=yShift;

			            }
			          
			            g2d.drawString("-------------------------------------",10,y);y+=yShift;
			            g2d.drawString(" Total amount:               "+txttotalAmount.getText()+"   ",10,y);y+=yShift;
			            g2d.drawString("-------------------------------------",10,y);y+=yShift;
			            g2d.drawString(" Cash      :                 "+txtcash.getText()+"   ",10,y);y+=yShift;
			            g2d.drawString("-------------------------------------",10,y);y+=yShift;
			            g2d.drawString(" Balance   :                 "+balance+"   ",10,y);y+=yShift;
			  
			            g2d.drawString("*************************************",10,y);y+=yShift;
			            g2d.drawString("       THANK YOU COME AGAIN          ",10,y);y+=yShift;
			            g2d.drawString("*************************************",10,y);y+=yShift;
			            g2d.drawString("       SOFTWARE BY:PARABEUS          ",10,y);y+=yShift;
			            g2d.drawString("CONTACT: parabeuspeluqueria@gmail.com",10,y);y+=yShift;  
			           

			    }
			    catch(Exception e){
			    e.printStackTrace();
			    }

			              result = PAGE_EXISTS;    
			          }    
			          return result;    
			      }
			   }
			    
	
	}
	*/

	@Override
	public void windowOpened(WindowEvent e) {
        //Para el ComboBox
        cargarPrecios();
        vista.actualizarComboBoxCodigoServicio();
		
	}

	@Override
	public void windowClosing(WindowEvent e) {

		guardarPrecios();

	}

	@Override
	public void windowClosed(WindowEvent e) {

		guardarPrecios();
		
	}
}
