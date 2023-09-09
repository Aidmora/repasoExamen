
import LecturaArchivos.LecturaArchivo;
import UserInterface.*;
public class App {
    public static  String directorioHorario= ".\\MNCoordenadas";
    public static void main(String[] args) throws Exception {
        // LecturaArchivo lc= new LecturaArchivo();
        // lc.LeerArchivos(directorioHorario);
        LecturaArchivo lc= new LecturaArchivo();
        lc.LeerArchivos(directorioHorario);
        new login().setVisible(true);
        
    }
}
