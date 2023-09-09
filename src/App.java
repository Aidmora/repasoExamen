import DataAccess.LecturaArchivo;
import UserInterface.*;
public class App {
    public static  String directorioHorario= ".\\Libreria";
    public static void main(String[] args) throws Exception {
        LecturaArchivo lc= new LecturaArchivo();
        lc.LeerArchivos(directorioHorario);
        BibliotecaMenu menu= new BibliotecaMenu();
        menu.mostrarMenu();
    }
}
