package Vista.Administracion;

import javax.swing.*;
import java.awt.*;

public class CierreCajaView extends JPanel {

    public CierreCajaView() {
        setLayout(new BorderLayout());
        inicializarComponentes();
    }

    private void inicializarComponentes() {
        JLabel enConstruccionLabel = new JLabel("En Construcción");
        enConstruccionLabel.setFont(new Font("Arial", Font.BOLD, 24)); // Cambia el tamaño de la fuente a 24
        add(enConstruccionLabel, BorderLayout.NORTH);
    };
}
