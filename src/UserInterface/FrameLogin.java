package UserInterface;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import FrameWork.AppException;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
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

import BusinessLogic.MNUsuarioBL;
import BusinessLogic.Entities.MNUsuario;

public class FrameLogin extends JFrame implements ActionListener{
    private JLabel mnLblUsuario, mnLblClave;
    private JTextField mnTxtUsuario;
    private JPasswordField mnPswClave;
    private GridBagConstraints mnConstraints;
    private JButton mnBtnIngresar;
    private JPanel mnPanel, mnPanelBoton;
    private int mnIntentos;
    String mnText1,mnText2;
    private final int    MN_ANCHO  = 1200;
    private final int    MN_ALTO   = 600;
    
    /*
     * Constructor de la clase FrameLogin
     */
    public FrameLogin (){
        mnSetCustomization();
        mnInitComponents();
        mnAddComponents();

        
        mnBtnIngresar.addActionListener(this);
    }

    /*
     * mnInitComponents: inicializa los componentes y asigna las propiedades que cada uno va a tener
     * cuando se muestre en pantalla.
     */
    public void mnInitComponents() {
        Font mnFuente   = new Font("Arial", Font.BOLD, 25); 
        Font mnFuente2  = new Font("Arial", Font.PLAIN, 25); 
        mnLblUsuario    = new JLabel("Usuario");
        mnLblClave      = new JLabel("Contraseña");
        mnTxtUsuario    = new JTextField(15);
        mnPswClave      = new JPasswordField(15);
        mnBtnIngresar   = new JButton("Ingresar");
        mnPanel         = new JPanel();
        mnPanelBoton    = new JPanel();
        mnLblUsuario.setFont(mnFuente);
        mnLblClave.setFont(mnFuente);
        mnTxtUsuario.setFont(mnFuente2);
        mnPswClave.setFont(mnFuente2);
        mnBtnIngresar.setFont(mnFuente2);

        mnIntentos = 0;

        mnBtnIngresar.setPreferredSize(new Dimension(150, 50));
        mnPanel.setLayout(new GridBagLayout());
        mnPanelBoton.setLayout(new GridBagLayout());

        mnConstraints = new GridBagConstraints();
        mnConstraints.insets = new Insets(10, 10, 10, 10);

        setSize(MN_ANCHO, MN_ALTO);
        setLocationRelativeTo(null);
    }

    /*
     * mnSetCustomization: establece las propiedades personalizadas para la clase que hereda de
     * JFrame.
     */
    public void mnSetCustomization() {
        setTitle("Bienvenido...");
        setLayout(new BorderLayout()); 
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
    }

    /*
     * mnAddComponents: agrega cada componente a su contenedor para dar estructura a la presentación
     * de cada elemento en la pantalla.
     */
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

        mnConstraints.insets = new Insets(0,0,100,0);
        mnConstraints.gridx = 0; 
        mnConstraints.gridy = 0;
        mnPanelBoton.add(mnBtnIngresar, mnConstraints);
        add(mnPanelBoton, BorderLayout.SOUTH);

    }


     /**
      * Método sobreescrito que actúa como un action event para los botones de la clase.
      * @param ActionEvent un evento de tipo action event.
      */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == mnBtnIngresar) {
            String mnUsuarioIng    = mnTxtUsuario.getText();
            String mnContrasenaIng = new String(mnPswClave.getPassword());

            try {
                if (mnValidarCredenciales(mnUsuarioIng, mnContrasenaIng)) {
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
            } catch (HeadlessException | AppException e1) {
                e1.printStackTrace();
            } catch (IOException e1) {
                e1.printStackTrace();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
    }

    /**
     * mnValidarCredenciales: se encarga de obtener el nombre de usuario y la contrasenia ingresadas en los campos
     * JTextField y JPasswordField para luego encriptar la contrasenia y validar si es un usuario registrado.
     * @param mnUsuarioIng el nombre del usuario ingresado
     * @param mnClaveIng contrasenia ingresada por el usuario
     * @return true si los datos proporcionados se encuentran en la base de datos, caso contrario false
     * @throws AppException
     */
    private boolean mnValidarCredenciales(String mnUsuarioIng, String mnClaveIng) throws AppException {
        List<MNUsuario> mnListaUsuarios = new MNUsuarioBL().mnGetAll();

        for (MNUsuario mnUsuario : mnListaUsuarios) {
            if (mnUsuario.getNombreUsuario().equals(mnUsuarioIng))
                if (mnUsuario.getClaveUsuario().equals(mnEncriptar(mnClaveIng)))
                    return true;
        }
        return false;
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
