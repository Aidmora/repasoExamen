package BusinessLogic.Facade;

import java.util.List;

import DataAccess.MNCoordenadasDAC;
import FrameWork.AppExceptionAriel;

public class MNCoordenadasBL {
    MNCoordenadasDAC mnCoordenadasDAC;
    public MNCoordenadasBL () throws AppExceptionAriel{
        mnCoordenadasDAC= new MNCoordenadasDAC();
    }
    public void mnSetAllData (List <String> mnCoordenadas){
        for (String mnCoordenadasStr : mnCoordenadas) {
            mnCoordenadasDAC.mnInsertarDatos(mnCoordenadasStr);
        }
    }
}