package BusinessLogic.Facade;
import java.util.List;

import DataAccess.MNHorarioDAC;
import FrameWork.AppExceptionAriel;
public class MNHorarioBl {
    MNHorarioDAC mnHorarioDAC;
    public MNHorarioBl () throws AppExceptionAriel{
        mnHorarioDAC= new MNHorarioDAC();
    }
    public void mnSetAllData(List <String> mnHorarioDia, List <String> mnHorario ){

        for (int i = 0; i < mnHorarioDia.size(); i++) {
            mnHorarioDAC.mnInsertarDatos(mnHorarioDia.get(i), mnHorario.get(i));
        }
    }

}
