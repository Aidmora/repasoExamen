package UserInterface;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import FrameWork.AppException;

import java.awt.*;
import java.io.IOException;
import java.sql.SQLException;

public class FrameTabla extends JFrame {
    private final String MN_TITULO = "Tabla";
    private final int    MN_ANCHO  = 1000;
    private final int    MN_ALTO   = 600;
    private JPanel mnPanelTitulo;
    private JLabel mnLblTitulo;

    public FrameTabla() throws IOException, AppException, SQLException {
        mnSetCustomization();
        mnInitComponents();
        addComponents();
    }

    private void mnSetCustomization() {
        setTitle(MN_TITULO);
        setSize(MN_ANCHO, MN_ALTO);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        setResizable(true);             // cambiar esta propiedad
        setDefaultCloseOperation(EXIT_ON_CLOSE);

    }

    private void mnInitComponents() {
        mnLblTitulo = new JLabel("Datos Interceptados");
        mnPanelTitulo = new JPanel();

        mnPanelTitulo.setLayout(new FlowLayout());
    }

    private void addComponents() throws IOException, AppException, SQLException {
        mnPanelTitulo.add(mnLblTitulo);
        add(mnPanelTitulo ,BorderLayout.NORTH);
        add(new PanelTabla(), BorderLayout.CENTER);
        // add(new JPanel().add(mnLblTitulo), BorderLayout.NORTH);
    }

}