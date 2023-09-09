package LecturaArchivos;

import BusinessLogic.MNArsenalBL;
import BusinessLogic.MNArsenalTipoBL;
import BusinessLogic.MNCoordenadasBL;
import BusinessLogic.MNHorarioBl;
import FrameWork.AppException;

public class MNCargarArchivos {
    private MNLecturaArchivo lecturaArchivo;

    public MNCargarArchivos(MNLecturaArchivo lecturaArchivo){
        this.lecturaArchivo = lecturaArchivo;

    }

    public void mnCargar() throws AppException {
        new MNArsenalBL().mnSetAllData(lecturaArchivo.mnArsenalNombre);
        new MNArsenalTipoBL().mnSetAllData(lecturaArchivo.mnArsenalTipoNombre);
        new MNCoordenadasBL().mnSetAllData(lecturaArchivo.mnCoordenadas);
        new MNHorarioBl().mnSetAllData(lecturaArchivo.mnHorarioDia,lecturaArchivo.mnHorario);
        System.out.println("[+] Guardando:" +"\n"
                            + " -Arsenal Tipo....."+"\n"
                            + " -Coordenadas....."+"\n"
                            + " -Arsenal....."+"\n"
                            + " -Horarios.....");
        

    }
}
