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
import LecturaArchivos.LecturaArchivo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.Insets;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import BusinessLogic.Entities.MNUsuario;
import BusinessLogic.Facade.MNUsuarioBL;

public class FrameLogin extends JFrame implements ActionListener{
    private JLabel mnLblUsuario, mnLblClave;
    private JTextField mnTxtUsuario;
    private JPasswordField mnPswClave;
    private GridBagConstraints mnConstraints;
    private JButton mnBtnIngresar;
    private JPanel mnPanel, mnPanelBoton;
    private int mnIntentos;
    String mnText1,mnText2;
    
    public FrameLogin (){
        mnSetCustomization();
        mnInitComponents();
        mnAddComponents();

        
        mnBtnIngresar.addActionListener(this);
    }

    public void mnInitComponents() {
        mnLblUsuario    = new JLabel("Usuario");
        mnLblClave      = new JLabel("Contraseña");
        mnTxtUsuario    = new JTextField(15);
        mnPswClave      = new JPasswordField(15);
        mnBtnIngresar   = new JButton("Ingresar");
        mnPanel         = new JPanel();
        mnPanelBoton    = new JPanel();
        mnIntentos = 0;

        mnBtnIngresar.setBackground(Color.GREEN); 
        mnBtnIngresar.setPreferredSize(new Dimension(150, 50));
        mnPanel.setLayout(new GridBagLayout());
        mnPanelBoton.setLayout(new FlowLayout());

        mnConstraints = new GridBagConstraints();
        mnConstraints.insets = new Insets(10, 10, 10, 10);

        setSize(800, 600);
        setLocationRelativeTo(null);
    }

    public void mnSetCustomization() {
        setTitle("Bienvenido...");
        setLayout(new BorderLayout()); 

        
    }

    public void mnAddComponents() {
        mnConstraints.gridx = 0; 
        mnConstraints.gridy = 0;
        mnPanel.add(mnLblUsuario, mnConstraints);
        
        mnConstraints.gridy = 1;
        mnPanel.add(mnTxtUsuario, mnConstraints);
        
        mnConstraints.gridy = 2;
        mnPanel.add(mnLblClave, mnConstraints);

        mnConstraints.gridy = 3;
        mnPanel.add(mnPswClave, mnConstraints);
        
        add(mnPanel, BorderLayout.CENTER);
        add(new JPanel().add(mnBtnIngresar), BorderLayout.SOUTH);

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == mnBtnIngresar) {
            String mnUsuarioIng    = mnTxtUsuario.getText();
            String mnContrasenaIng = new String(mnPswClave.getPassword());

            try {
                if (validarCredenciales(mnUsuarioIng, mnContrasenaIng)) {
                    JOptionPane.showMessageDialog(null, "¡Sesión Iniciada!");
                    setVisible(false);
                    new FrameTabla().setVisible(true);;
                    
                } else {
                    mnIntentos++;
                    mnTxtUsuario.setText("");
                    mnPswClave.setText("");
                    JOptionPane.showMessageDialog(null, "Usuario o Contraseña Incorrectos!");

                    if (mnIntentos >= 3) {
                        JOptionPane.showMessageDialog(null, "Lo sentimos, intentos agotados!");
                        System.exit(0); 
                    }
                }
            } catch (HeadlessException | AppExceptionAriel e1) {
                e1.printStackTrace();
            } catch (IOException e1) {
                e1.printStackTrace();
            } catch (SQLException e1) {
                // TODO Auto-generated catch block
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
