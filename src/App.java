

import LecturaArchivos.MNCargarArchivos;
import UserInterface.*;
public class App {
    private static final String MN_NOMBRE_ARIEL = "Mora Jarrín Ariel David";
    private static final String MN_CEDULA_ARIEL = "1724681521";
    private static final String MN_NOMBRE_FERNANDO = "Nagua Uyaguari Fernando Alexander";
    private static final String MN_CEDULA_FERNANDO = "1724721301";
    public static void main(String[] args) throws Exception {
        
        System.out.println("\n\t[+]" + MN_NOMBRE_ARIEL);
        System.out.println("\t\t[-]" + MN_CEDULA_ARIEL);
        System.out.println("\n\t[+]" + MN_NOMBRE_FERNANDO);
        System.out.println("\t\t[-]" + MN_CEDULA_FERNANDO);
        System.out.println("\n\n");

        new FrameLogin().setVisible(true);
        
        
    }
}
