package DataAccess;

import java.awt.List;
import java.sql.ResultSet;
import java.sql.SQLException;

import FrameWork.AppExceptionAriel;

public class MNCoordenadasDAC extends SQLiteDataHelper {
    public MNCoordenadasDAC() throws AppExceptionAriel {
        super();
    }

    public void mnInsertarDatos(String mnDato){
        String insertSQL= "INSERT INTO MN_COORDENADAS" + " (Coordenada ) " +
           "VALUES ( ?)";
        try  {
            mnPreparedSt = mnConn.prepareStatement(insertSQL);
            String [] mnCoor= mnDato.split("-");
            String mnCoorNum= mnCoor[0].trim();
            mnPreparedSt.setString(1,mnCoorNum );
            mnPreparedSt.executeUpdate();       
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ResultSet mnGetAll() throws AppExceptionAriel {
        mnConsultaSQL= "SELECT Coordenada  FROM MN_COORDENADAS";
        
        return mnGetResulSet(mnConsultaSQL);    
    }
}
