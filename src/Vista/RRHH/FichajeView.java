package Vista.RRHH;

import javax.swing.*;
import java.awt.*;

public class FichajeView extends JPanel {

    public FichajeView() {
        setLayout(new BorderLayout());
        inicializarComponentes();
    }

    private void inicializarComponentes() {
        JLabel enConstruccionLabel = new JLabel("En Construcción");
        enConstruccionLabel.setFont(new Font("Arial", Font.BOLD, 24)); // Cambia el tamaño de la fuente a 24
        add(enConstruccionLabel, BorderLayout.NORTH);
    };
}
