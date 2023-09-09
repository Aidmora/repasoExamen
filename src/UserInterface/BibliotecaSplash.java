package UserInterface;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JWindow;
import javax.swing.SwingUtilities;

public class BibliotecaSplash   {
    private JWindow bibliotecaSplash;
    private static final int ANCHO_PANTALLA = 310;
    private static final int ALTO_PANTALLA = 730;
    
    public void mostrarPantallazo() {
        bibliotecaSplash=new JWindow();
        Dimension tamañoPantalla = Toolkit.getDefaultToolkit().getScreenSize();
        bibliotecaSplash.setLocation((tamañoPantalla.width - ANCHO_PANTALLA) / 2, 
        (tamañoPantalla.height - ALTO_PANTALLA) / 2);
        bibliotecaSplash.setSize( ANCHO_PANTALLA, ALTO_PANTALLA);
        
        ImageIcon bibliotecaImagen= new ImageIcon("src\\UserInterface\\Img\\biblioteca.png");
        Image imagenAEscalonar= bibliotecaImagen.getImage();
        Image imagenEscalonada=imagenAEscalonar.getScaledInstance(ANCHO_PANTALLA ,
        ALTO_PANTALLA , Image.SCALE_SMOOTH);
        
        ImageIcon pantallazoLogoEscalonado=new ImageIcon(imagenEscalonada);
        JLabel etiquetaPantallazo = new JLabel(pantallazoLogoEscalonado);
        bibliotecaSplash.getContentPane().add(etiquetaPantallazo);
        bibliotecaSplash.setVisible(true);
        try {
            
            Thread.sleep(5000);
            bibliotecaSplash.dispose();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
    }
}
