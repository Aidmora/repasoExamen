package DataAccess;

import java.sql.ResultSet;
import java.sql.SQLException;

import FrameWork.AppException;

public class MNHorarioDAC extends SQLiteDataHelper {
    public MNHorarioDAC() throws AppException {
        super();
    }
    /**
     * mnInsertarDatos: Este metodo se encarga de insertar informacion en la base de datos, especificamente
     * en la tabla "MN_HORARIO"
     * @param mnDia: Representa al día
     * @param mnHora: Representa la hora.
     */
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
    /**
     * mnGetAll: Este metodo permite obtener la informacion de la base de datos y lo almacena en un ResultSet.
     */
    @Override
    public ResultSet mnGetAll() throws AppException {
        return null;
    }

    
}
