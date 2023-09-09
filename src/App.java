import DataAccess.LecturaArchivo;
import UserInterface.*;
public class App {
    public static  String directorioHorario= ".\\Libreria";
    public static void main(String[] args) throws Exception {
        LecturaArchivo lc= new LecturaArchivo();
        lc.LeerArchivos(directorioHorario);
        login lg= new login();
        lg.mostrarPantalla();
    }
}
