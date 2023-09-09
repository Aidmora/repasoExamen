package BusinessLogic;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import BusinessLogic.Entities.MNCoordenadas;
import DataAccess.MNCoordenadasDAC;
import FrameWork.AppException;

public class MNCoordenadasBL {
    MNCoordenadasDAC mnCoordenadasDAC;
    public MNCoordenadasBL () throws AppException{
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
    /**
     * mnGetAll: Este metodo se encarga de insertar la informacion obtenida por el DAC,  en una lista 
     * correspondiente a Coordenadas. 
     * @return List<MNCoordenadas>: Representa a una lista de Coordenadas.
     * @throws AppException
     */
    public List<MNCoordenadas> mnGetAll() throws AppException {
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
            throw new AppException(e, getClass(), "mnGetAll()");
        }
    }
}