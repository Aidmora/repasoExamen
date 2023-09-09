package UserInterface;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import DataAccess.MNUsuarioDAC;
import FrameWork.AppExceptionAriel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.Insets;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import BusinessLogic.Entities.MNUsuario;
import BusinessLogic.Facade.MNUsuarioBL;

public class login extends JFrame implements ActionListener{
    private JLabel lblUsuario, lblClave;
    private JTextField mnTxtUsuario;
    private JPasswordField mnPswClave;
    private GridBagConstraints constraints;
    private JButton boton1;
    private JPanel mnPanel;
    private int intentos;
    static login label = new login();
    String textoStr,texto2Str;
    
    public login (){
        mnSetCustomization();
        mnInitComponents();
        mnAddComponents();

        
        boton1.addActionListener(this);
    }

    public void mnInitComponents() {
        lblUsuario      = new JLabel("Usuario");
        lblClave        = new JLabel("Contraseña");
        mnTxtUsuario    = new JTextField(15);
        mnPswClave      = new JPasswordField(15);
        boton1          = new JButton("Ingresar");
        mnPanel         = new JPanel();
        intentos = 0;

        boton1.setBackground(Color.BLACK); 
        mnPanel.setLayout(new GridBagLayout());

        constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, 10);

        setSize(800, 600);
        setLocationRelativeTo(null);
    }

    public void mnSetCustomization() {
        setTitle("Bievenido...");
        setLayout(new BorderLayout()); 

        
    }

    public void mnAddComponents() {
        constraints.gridx = 0; 
        constraints.gridy = 0;
        mnPanel.add(lblUsuario, constraints);
        
        constraints.gridy = 1;
        mnPanel.add(mnTxtUsuario, constraints);
        
        constraints.gridy = 2;
        mnPanel.add(lblClave, constraints);

        constraints.gridy = 3;
        mnPanel.add(mnPswClave, constraints);
        
        add(mnPanel, BorderLayout.CENTER);
        add(boton1, BorderLayout.SOUTH);

    }

    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == boton1) {
            String mnUsuarioIng    = mnTxtUsuario.getText();
            String mnContrasenaIng = new String(mnPswClave.getPassword());

            try {
                if (validarCredenciales(mnUsuarioIng, mnContrasenaIng)) {
                    JOptionPane.showMessageDialog(null, "¡Sesión Iniciada!");
                    label.setVisible(false); 
                    
                } else {
                    intentos++;
                    mnTxtUsuario.setText("");
                    mnPswClave.setText("");
                    JOptionPane.showMessageDialog(null, "Usuario o Contraseña Incorrectos!");

                    if (intentos >= 3) {
                        JOptionPane.showMessageDialog(null, "Lo sentimos, intentos agotados!");
                        System.exit(0); 
                    }
                }
            } catch (HeadlessException | AppExceptionAriel e1) {
                e1.printStackTrace();
            }
        }
    }

    private boolean validarCredenciales(String mnUsuarioIng, String mnClaveIng) throws AppExceptionAriel {
        List<MNUsuario> mnListaUsuarios = new MNUsuarioBL().mnGetAll();

        for (MNUsuario mnUsuario : mnListaUsuarios) {
            if (mnUsuario.getNombreUsuario().equals(mnUsuarioIng))
                if (mnUsuario.getClaveUsuario().equals(mnEncriptar(mnClaveIng)))
                    return true;
        }
        return false;
    }
    
    // public void mostrarPantalla(){
    //     BibliotecaSplash bibliotecaSplash= new BibliotecaSplash();
    //     bibliotecaSplash.mostrarPantallazo();
    //     label.setBounds(0, 0, 310, 730);
    //     label.setVisible(true);
    //     label.setLocationRelativeTo(null);
    // }

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
