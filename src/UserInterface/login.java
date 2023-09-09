package UserInterface;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import DataAccess.MNUsuarioDAC;
import FrameWork.AppExceptionAriel;

import java.awt.Color;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
public class login extends JFrame implements ActionListener{
    private JLabel etiquieta, etiquieta2;
    private JTextField texto;
    private JPasswordField texto2;
    private JButton boton1;
    private static int intentos=0;
    static login label = new login();
    String textoStr,texto2Str;
    
    public login (){
        mnSetCustomization();
        mnInitComponents();
        mnAddComponents();

        
        boton1.addActionListener(this);
    }

    public void mnInitComponents() {
        etiquieta = new JLabel("<html><font color='#e2c15c'>Correo electrónico</font></html>");
        etiquieta2 = new JLabel("<html><font color='#e2c15c'>Contraseña</font></html>");
        texto= new JTextField();
        texto2= new JPasswordField();
        boton1= new JButton("<html><font color='#e2c15c'>Iniciar sesión</font></html>");

    }

    public void mnSetCustomization() {
        setTitle("Bievenido...");
        setLayout(null); 
        etiquieta.setBounds(30, 160, 200, 35);
        etiquieta2.setBounds(30, 220, 200, 35);
        
        texto.setBounds(30,190,250,30);
        texto2.setBounds(30,250,250,30);

        boton1.setBounds(70, 520, 155, 30);
        boton1.setBackground(Color.BLACK); 
    }

    public void mnAddComponents() {
        add(etiquieta);
        add(etiquieta2);
        add(texto);
        add(texto2);
        add(boton1);

    }

    

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == boton1) {
            String mnUsuarioIng    = texto.getText();
            String mnContrasenaIng = new String(texto2.getPassword());

            if (validarCredenciales(mnUsuarioIng, mnContrasenaIng)) {
                JOptionPane.showMessageDialog(null, "¡Sesión Iniciada!");
                label.setVisible(false); 
                try {
                   BibliotecaMenu  menu = new BibliotecaMenu();
                   menu.mostrarMenu();
                } catch (AppExceptionAriel e1) {
                    e1.printStackTrace();
                }
                
            } else {
                intentos++;
                texto.setText("");
                texto2.setText("");
                JOptionPane.showMessageDialog(null, "Usuario o Contraseña Incorrectos!");

                if (intentos >= 3) {
                    JOptionPane.showMessageDialog(null, "Lo sentimos, intentos agotados!");
                    System.exit(0); 
                }
            }
        }
    }

    private boolean validarCredenciales(String mnUsuarioIng, String mnClaveIng) {
        List<MNUsuario> mnListaUsuarios = new MNUsuarioDAC().mnGetAll();

        for (UserInterface.mnUsuario mnUsuario : mnListaUsuarios) {
            if (mnUsuario.getNombreUsuario.equals(mnUsuarioIng))
                if (mnUsuario.getContraseniaUsuario.equals(mnEncriptar(mnClaveIng)))
                    return true;
        }
        return false;
    }
    
    public void mostrarPantalla(){
        BibliotecaSplash bibliotecaSplash= new BibliotecaSplash();
        bibliotecaSplash.mostrarPantallazo();
        label.setBounds(0, 0, 310, 730);
        label.setVisible(true);
        label.setLocationRelativeTo(null);
    }
    public String mnEncriptar(String mnContrasena) {

        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] inputBytes = mnContrasena.getBytes();
            byte[] hashBytes = md.digest(inputBytes);

            StringBuilder hexStringBuilder = new StringBuilder();
            for (byte b : hashBytes) {
                String hex = String.format("%02x", b);
                hexStringBuilder.append(hex);
            }

            return  hexStringBuilder.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
}

}
