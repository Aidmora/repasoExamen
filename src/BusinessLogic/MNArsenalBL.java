package BusinessLogic;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import BusinessLogic.Entities.MNArsenal;
import BusinessLogic.Entities.MNArsenalTipo;
import DataAccess.MNArsenalDAC;
import FrameWork.AppException;

public class MNArsenalBL {
    MNArsenalDAC mnArsenalDAC;
    public MNArsenalBL() throws AppException{
       mnArsenalDAC= new MNArsenalDAC(); 
    }
    /**
     * mnSetAllData: Este método se encarga de mandar la informacion que hay en la lista Arsenal, al DAC.
     * @param mnArsenal: Representa a la lista que contiene el los elementos del Arsenal.
     */
    public void mnSetAllData (List <String> mnArsenal){
        for (String mnArsnealStr : mnArsenal) {
            mnArsenalDAC.mnInsertarDatos(mnArsnealStr);
        }
    }
    public List<MNArsenal> mnGetAll() throws AppException {
        ResultSet mnRs = mnArsenalDAC.mnGetAll();
        List<MNArsenal> mnListaArsenal = new ArrayList<>();
        MNArsenal mnArsenal;
        try {
            while (mnRs.next()) {
                mnArsenal = new MNArsenal();
                mnArsenal.setNombreArsenal(mnRs.getString(2));
                mnListaArsenal.add(mnArsenal);
            }
                mnRs.close();
                return mnListaArsenal;
        } catch (SQLException e) {
            throw new AppException(e, getClass(), "mnGetAll()");
        }
    }
}
