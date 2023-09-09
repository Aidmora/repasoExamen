package LecturaArchivos;

import BusinessLogic.Facade.MNArsenalBL;
import BusinessLogic.Facade.MNArsenalTipoBL;
import BusinessLogic.Facade.MNCoordenadasBL;
import BusinessLogic.Facade.MNHorarioBl;
import FrameWork.AppExceptionAriel;

public class MNCargarArchivos {
    private LecturaArchivo lecturaArchivo;

    public MNCargarArchivos(LecturaArchivo lecturaArchivo){
        this.lecturaArchivo = lecturaArchivo;

    }

    public void mnCargar() throws AppExceptionAriel {
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
