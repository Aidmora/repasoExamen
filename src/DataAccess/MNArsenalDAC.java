package DataAccess;

import java.sql.ResultSet;
import java.sql.SQLException;

import FrameWork.AppExceptionAriel;

public class MNArsenalDAC extends SQLiteDataHelper {
    public MNArsenalDAC() throws AppExceptionAriel {
        super();
    }

    public void mnInsertarDatos(String mnDato){
        String insertSQL= "INSERT INTO MN_ARSENAL" + " (NombreArsenal) " +
           "VALUES ( ?)";
        try  {
            mnPreparedSt = mnConn.prepareStatement(insertSQL);
            mnPreparedSt.setString(1,mnDato );
            mnPreparedSt.executeUpdate();       
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ResultSet mnGetAll() throws AppExceptionAriel {
        mnConsultaSQL= "SELECT NombreArsenal  FROM MN_ARSENAL";
        
        return mnGetResulSet(mnConsultaSQL); 
    }
}
