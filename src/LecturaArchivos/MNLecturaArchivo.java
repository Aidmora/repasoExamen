package LecturaArchivos;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import FrameWork.AppException;
public class MNLecturaArchivo {
    public List<String> mnLines;
    public List<String> mnArsenalTipo=new ArrayList<>();
    public List<String> mnCoordenadas=new ArrayList<>();
    public List<String> mnArsenal=new ArrayList<>();
    public List<String> mnHorario=new ArrayList<>();
    public List<String> mnHorarioDia=new ArrayList<>();
    public List<String> mnArsenalNombre= new ArrayList<>();
    public HashSet<String> mnArsenalTipoNombre = new HashSet<>();
    public String mnNombreArsenal="";
    /**
     * LeerArchivos: Este metodo se encarga de leer el archivo .csv 
     * @param directorioHorario
     * @throws IOException
     * @throws AppException
     * @throws SQLException
     */
    public void LeerArchivos(String directorioHorario) throws IOException, AppException, SQLException{
        File f = new File(directorioHorario);
        if(f.isDirectory()){
            String[] fileNames = f.list();
            for (String nomArchivo : fileNames) {
                if(nomArchivo.endsWith(".csv")){
                    String nombreArchivo= directorioHorario+"\\"+nomArchivo;
                    mnLines = Files.readAllLines(Paths.get(nombreArchivo));

                    for (String line : mnLines) {
                        String[] values = line.split(";");
                        if(values[0].toLowerCase().trim().contains("id")){
                        continue;
                        }
                        mnCoordenadas.add(values[1]);
                        mnArsenal.add(values[7]);

                        for (int i = 2; i <7 ; i++) {
                            if (!values[i].trim().isEmpty()) {
                                mnHorario.add(values[i]);
                                if(i==2) mnHorarioDia.add("Lunes");
                                if(i==3) mnHorarioDia.add("Martes");
                                if(i==4) mnHorarioDia.add("Miercoles");
                                if(i==5) mnHorarioDia.add("Jueves");
                                if(i==6) mnHorarioDia.add("Viernes");
                                continue;

                            }
                            
                        }
                        if(values[1].contains("00")){
                            mnHorarioDia.add("");
                            mnHorario.add("");
                        }


                    }
                }else{
                    System.out.println(";/ Error en directorioHorario: "+ directorioHorario);
                }
            }
        }
        mnObtenerNombreArsenal(mnArsenal);
        mnObtenerTipoAsenal(mnArsenal);

        System.out.println("[+] Leyendo:" +"\n"
                            + " -Arsenal Tipo....."+"\n"
                            + " -Coordenadas....."+"\n"
                            + " -Arsenal....."+"\n"
                            + " -Horarios.....");
    }
    /**
     * mnObtenerNombreArsenal: Este metodo se encarga de obtener el nombre del código correspondiente al arsenal.
     * @param mnArsenal: Representa a la lista que tiene los códigos del arsenal.
     * @return
     */
    public List<String> mnObtenerNombreArsenal(List <String> mnArsenal){
        for (String mnCodigo : mnArsenal) {
            if(mnCodigo.equals("a"))mnNombreArsenal="Avion";
            if(mnCodigo.equals("ab"))mnNombreArsenal="Avion,Barco";
            if(mnCodigo.equals("abcdt"))mnNombreArsenal="Avion,Barco,Convoy,Dron,Tanque";
            if(mnCodigo.equals("abc"))mnNombreArsenal="Avion,Barco,Convoy";
            if(mnCodigo.equals("abcd"))mnNombreArsenal="Avion,Barco,Convoy,Dron";
            mnArsenalNombre.add(mnNombreArsenal);
        }
        return mnArsenalNombre;
    }
    /**
     * mnObtenerTipoAsenal: Este método se encarga de obtener  el tipo de arsenal a partir del "código"
     * @param mnArsenal: Representa a la lista que tiene los códigos del arsenal.
     */
    private HashSet <String> mnObtenerTipoAsenal(List<String> mnArsenal) {
        for (String mnTipoArsenal : mnArsenal) {
            for (int i = 0; i < mnTipoArsenal.length(); i++) {
                if (mnTipoArsenal.charAt(i) == 'a') {
                    mnArsenalTipoNombre.add("Aéreo");
                }
                if (mnTipoArsenal.charAt(i) == 'b') {
                    mnArsenalTipoNombre.add("Marítimo");
                }
                if (mnTipoArsenal.charAt(i) == 'c') {
                    mnArsenalTipoNombre.add("Terrestre");
                }
                if (mnTipoArsenal.charAt(i) == 'd') {
                    mnArsenalTipoNombre.add("Aéreo");
                }
                if (mnTipoArsenal.charAt(i) == 't') {
                    mnArsenalTipoNombre.add("Terrestre");
                }
            }

        }
        return mnArsenalTipoNombre;
    }

}
 