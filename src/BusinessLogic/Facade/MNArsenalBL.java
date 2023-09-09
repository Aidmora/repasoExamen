package BusinessLogic.Facade;

import java.util.List;

import DataAccess.MNArsenalDAC;
import FrameWork.AppExceptionAriel;

public class MNArsenalBL {
    MNArsenalDAC mnArsenalDAC;
    public MNArsenalBL() throws AppExceptionAriel{
       mnArsenalDAC= new MNArsenalDAC(); 
    }
    public void mnSetAllData (List <String> mnArsenal){
        for (String mnArsnealStr : mnArsenal) {
            mnArsenalDAC.mnInsertarDatos(mnArsnealStr);
        }
    }
}
