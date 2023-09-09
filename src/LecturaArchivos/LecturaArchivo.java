package LecturaArchivos;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.List;

import DataAccess.SQLiteDataHelper;
import FrameWork.AppExceptionAriel;
public class LecturaArchivo {
    public List<String> lines;
    public void LeerArchivos(String directorioHorario) throws IOException, AppExceptionAriel, SQLException{
        File f = new File(directorioHorario);
        if(f.isDirectory()){
            String[] fileNames = f.list();
            for (String nomArchivo : fileNames) {
                if(nomArchivo.endsWith(".csv")){
                    String nombreArchivo= directorioHorario+"\\"+nomArchivo;
                    lines = Files.readAllLines(Paths.get(nombreArchivo));
                    String nombreTabla= nomArchivo.substring(0,nomArchivo.lastIndexOf("."));
                    SQLiteDataHelper.crearTablaDesdeCSV(lines,nombreTabla);

                          
                }
            }
        }else{
            System.out.println(";/ Error en directorioHorario: "+ directorioHorario);
        }
    }

}
 