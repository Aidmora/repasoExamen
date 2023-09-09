package DataAccess;

import java.sql.ResultSet;
import java.sql.SQLException;

import FrameWork.AppException;

public class MNHorarioDAC extends SQLiteDataHelper {
    public MNHorarioDAC() throws AppException {
        super();
    }

    public void mnInsertarDatos(String mnDia, String mnHora){
        String insertSQL= "INSERT INTO MN_HORARIO" + " ( NombreDia, hora) " +
           "VALUES ( ?, ?)";
        try  {
            mnPreparedSt = mnConn.prepareStatement(insertSQL);
            mnPreparedSt.setString(1,mnDia );
            mnPreparedSt.setString(2,mnHora );
            mnPreparedSt.executeUpdate();       
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ResultSet mnGetAll() throws AppException {
        return null;
    }

    
}
