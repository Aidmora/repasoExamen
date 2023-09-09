package BusinessLogic.Facade;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import BusinessLogic.Entities.MNCoordenadas;
import BusinessLogic.Entities.MNHorario;
import DataAccess.MNCoordenadasDAC;
import FrameWork.AppExceptionAriel;

public class MNCoordenadasBL {
    MNCoordenadasDAC mnCoordenadasDAC;
    public MNCoordenadasBL () throws AppExceptionAriel{
        mnCoordenadasDAC= new MNCoordenadasDAC();
    }
    /**
     * mnSetAllData:  Este método se encarga de mandar la informacion que hay en la lista Coordenadas, al DAC. 
     * @param mnCoordenadas: Reprsenta la lista que contiene la informacion sobre las coordenadas.
     */
    public void mnSetAllData (List <String> mnCoordenadas){
        for (String mnCoordenadasStr : mnCoordenadas) {
            mnCoordenadasDAC.mnInsertarDatos(mnCoordenadasStr);
        }
    }
    public List<MNCoordenadas> mnGetAll() throws AppExceptionAriel {
        ResultSet mnRs = mnCoordenadasDAC.mnGetAll();
        List<MNCoordenadas> mnListaCoordenadas = new ArrayList<>();
        MNCoordenadas mnCoordenadas;
        try {
            while (mnRs.next()) {
                mnCoordenadas = new MNCoordenadas();
                mnCoordenadas.setCoordenada(mnRs.getString(2)); 
                mnListaCoordenadas.add(mnCoordenadas);
            }
                mnRs.close();
                return mnListaCoordenadas;
        } catch (SQLException e) {
            throw new AppExceptionAriel(e, getClass(), "mnGetAll()");
        }
    }
}