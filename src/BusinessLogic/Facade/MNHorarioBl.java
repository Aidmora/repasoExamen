package BusinessLogic.Facade;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import BusinessLogic.Entities.MNHorario;
import BusinessLogic.Entities.MNUsuario;
import DataAccess.MNHorarioDAC;
import FrameWork.AppExceptionAriel;
public class MNHorarioBl {
    MNHorarioDAC mnHorarioDAC;
    public MNHorarioBl () throws AppExceptionAriel{
        mnHorarioDAC= new MNHorarioDAC();
    }
    /**
     * mnSetAllData:Este método se encarga de mandar la informacion que hay en la lista Horario , al DAC. 
     * @param mnHorarioDia: Representa la lista que contiene los dias del horario.
     * @param mnHorario: Representa la lista que contiene la hora, correspondiente al dia.
     */
    public void mnSetAllData(List <String> mnHorarioDia, List <String> mnHorario ){

        for (int i = 0; i < mnHorarioDia.size(); i++) {
            mnHorarioDAC.mnInsertarDatos(mnHorarioDia.get(i), mnHorario.get(i));
        }
    }
        public List<MNHorario> mnGetAll() throws AppExceptionAriel {
        ResultSet mnRs = mnHorarioDAC.mnGetAll();
        List<MNHorario> mnListaHorario = new ArrayList<>();
        MNHorario mnHorario;
        try {
            while (mnRs.next()) {
                mnHorario = new MNHorario();
                
                mnHorario.setNombreDia(mnRs.getString(2));;
                mnHorario.setHora(mnRs.getString(3));   
                mnListaHorario.add(mnHorario);
            }
                mnRs.close();
                return mnListaHorario;
        } catch (SQLException e) {
            throw new AppExceptionAriel(e, getClass(), "mnGetAll()");
        }
    }

}
