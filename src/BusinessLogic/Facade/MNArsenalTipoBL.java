package BusinessLogic.Facade;

import java.util.HashSet;
import java.util.List;

import DataAccess.MNArsenalTipoDAC;
import FrameWork.AppExceptionAriel;

public class MNArsenalTipoBL {
    MNArsenalTipoDAC mnArsenalTipoDAC;
    public MNArsenalTipoBL () throws AppExceptionAriel{
       mnArsenalTipoDAC = new MNArsenalTipoDAC();
    }
    
    public  void mnSetAllData (HashSet<String> mnArsenalTipoNombre){
        for (String mnArsenalTipoStr : mnArsenalTipoNombre) {
            mnArsenalTipoDAC.mnInsertarDatos(mnArsenalTipoStr);
        }
    }
}
