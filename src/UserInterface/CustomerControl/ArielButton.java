package UserInterface.CustomerControl;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;

public class ArielButton extends JButton implements ActionListener{
    Font font = new Font ("MesloLGL Nerd Font", Font.BOLD | Font.LAYOUT_LEFT_TO_RIGHT, 12);
    public ArielButton ( String iconPath){
        goStyleMenu(iconPath);
    }
    public void goStyleMenu( String iconPath){ 
        ImageIcon libro= new ImageIcon(getClass().getResource(iconPath));
        setBounds(50, 50,30, 30);

        Image imagenLibro=libro.getImage();
        Image libroRenderizado= imagenLibro.getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
        ImageIcon biblioRender= new ImageIcon(libroRenderizado);
        setIcon(biblioRender);
 
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        
    }

}
