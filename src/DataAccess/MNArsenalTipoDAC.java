package DataAccess;

import java.sql.ResultSet;
import java.sql.SQLException;

import FrameWork.AppExceptionAriel;

public class MNArsenalTipoDAC extends SQLiteDataHelper {
    public MNArsenalTipoDAC() throws AppExceptionAriel {
        super();
    }

    public void mnInsertarDatos(String mnDato){
        String insertSQL= "INSERT INTO MN_ARSENALTIPO" + " (NombreArsenalTipo) " +
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
        return null;
    }
}
