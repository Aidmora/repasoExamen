package BusinessLogic;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import BusinessLogic.Entities.MNArsenalTipo;
import BusinessLogic.Entities.MNCoordenadas;
import DataAccess.MNArsenalTipoDAC;
import FrameWork.AppException;

public class MNArsenalTipoBL {
    MNArsenalTipoDAC mnArsenalTipoDAC;
    public MNArsenalTipoBL () throws AppException{
       mnArsenalTipoDAC = new MNArsenalTipoDAC();
    }
    /**
     * mnSetAllData: Este método se encarga de mandar la informacion que hay en la lista ArsenalTipo, al DAC. 
     * @param mnArsenalTipoNombre: Representa la lista que contiene la informacion del tipo de Arsenal.
     */
    public  void mnSetAllData (HashSet<String> mnArsenalTipoNombre){
        for (String mnArsenalTipoStr : mnArsenalTipoNombre) {
            mnArsenalTipoDAC.mnInsertarDatos(mnArsenalTipoStr);
        }
    }
    public List<MNArsenalTipo> mnGetAll() throws AppException {
        ResultSet mnRs = mnArsenalTipoDAC.mnGetAll();
        List<MNArsenalTipo> mnListaArsenalTipo = new ArrayList<>();
        MNArsenalTipo mnArsenalTipo;
        try {
            while (mnRs.next()) {
                mnArsenalTipo = new MNArsenalTipo();
                mnArsenalTipo.setNombreArsenalTipo(mnRs.getString(2));
                mnListaArsenalTipo.add(mnArsenalTipo);
            }
                mnRs.close();
                return mnListaArsenalTipo;
        } catch (SQLException e) {
            throw new AppException(e, getClass(), "mnGetAll()");
        }
    }
}
