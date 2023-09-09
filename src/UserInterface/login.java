package UserInterface;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import FrameWork.AppExceptionAriel;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class login extends JFrame implements ActionListener{
    private JLabel etiquieta, etiquieta2;
    private JTextField texto;
    private JPasswordField texto2;
    private JButton boton1;
    private static final String usuario="profe";
    private static final String contrasena="1234";
    private static int intentos=0;
    static login label= new login();
    String textoStr,texto2Str;
    public login (){
        setTitle("Bievenido...");
        setLayout(null); 
        etiquieta= new JLabel("<html><font color='#e2c15c'>Correo electrónico</font></html>");
        etiquieta2= new JLabel("<html><font color='#e2c15c'>Contraseña</font></html>");
        etiquieta.setBounds(30, 160, 200, 35);
        etiquieta2.setBounds(30, 220, 200, 35);
        add(etiquieta);
        add(etiquieta2);

        texto= new JTextField();
        texto2= new JPasswordField();
        texto.setBounds(30,190,250,30);
        texto2.setBounds(30,250,250,30);
        add(texto);
        add(texto2);

        boton1= new JButton("<html><font color='#e2c15c'>Iniciar sesión</font></html>");
        boton1.setBounds(70, 520, 155, 30);
        boton1.setBackground(Color.BLACK); 
        add(boton1);
        boton1.addActionListener(this);
    }
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == boton1) {
            String usuarioIngresado = texto.getText();
            String contrasenaIngresada = new String(texto2.getPassword());

            if (validarCredenciales(usuarioIngresado, contrasenaIngresada)) {
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

    private boolean validarCredenciales(String usuarioIngresado, String contrasenaIngresada) {
        return usuarioIngresado.equals(usuario) && contrasenaIngresada.equals(contrasena);
    }
    
    public void mostrarPantalla(){
        BibliotecaSplash bibliotecaSplash= new BibliotecaSplash();
        bibliotecaSplash.mostrarPantallazo();
        label.setBounds(0, 0, 310, 730);
        label.setVisible(true);
        label.setLocationRelativeTo(null);
    }
}
