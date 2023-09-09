import LecturaArchivos.LecturaArchivo;
import UserInterface.*;
public class App {
    public static  String directorioHorario= ".\\Libreria";
    public static void main(String[] args) throws Exception {
        // LecturaArchivo lc= new LecturaArchivo();
        // lc.LeerArchivos(directorioHorario);
        login lg= new login();
        String valorEncriptado=lg.encriptar("12345");
        String valorEncriptado2=lg.encriptar("1724681521");
        String valorEncriptado3=lg.encriptar("1724721301");
    }
}
