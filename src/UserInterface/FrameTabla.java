package UserInterface;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import FrameWork.AppException;

import java.awt.*;
import java.io.IOException;
import java.sql.SQLException;

public class FrameTabla extends JFrame {
    private final String MN_TITULO = "Examen Segundo Bimestre";
    private final int    MN_ANCHO  = 1200;
    private final int    MN_ALTO   = 600;
    private JPanel mnPanelTitulo;
    private JLabel mnLblTitulo;

    /**
     * Constructor de la clase FrameTabla
     * @throws IOException
     * @throws AppException
     * @throws SQLException
     */
    public FrameTabla() throws IOException, AppException, SQLException {
        mnSetCustomization();
        mnInitComponents();
        mnAddComponents();
    }

    /**
     * mnSetCustromization: establece una personalización a la ventana
     */
    private void mnSetCustomization() {
        setTitle(MN_TITULO);
        setSize(MN_ANCHO, MN_ALTO);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        setResizable(false);           
        setDefaultCloseOperation(EXIT_ON_CLOSE);

    }

    /**
     * mnInitComponents: inicializa los componentes que se estarán presentes en la ventana
     */
    private void mnInitComponents() {
        Font mnFuente = new Font("Arial", Font.BOLD, 20);     
        mnLblTitulo = new JLabel("Datos Interceptados");
        mnLblTitulo.setFont(mnFuente);
        mnPanelTitulo = new JPanel();
        mnPanelTitulo.setPreferredSize(new Dimension(0, 50));

        mnPanelTitulo.setLayout(new FlowLayout());
    }

    /**
     * mnAddComponents: agrega los componentes a cada contenedor para darle estructura a la interfaz gráfica
     * @throws IOException
     * @throws AppException
     * @throws SQLException
     */
    private void mnAddComponents() throws IOException, AppException, SQLException {
        mnPanelTitulo.add(mnLblTitulo);
        add(mnPanelTitulo ,BorderLayout.NORTH);
        add(new PanelTabla(), BorderLayout.CENTER);
    }

}