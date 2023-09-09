package DataAccess;

import java.sql.ResultSet;
import java.sql.SQLException;

import FrameWork.AppException;

public class MNArsenalDAC extends SQLiteDataHelper {
    public MNArsenalDAC() throws AppException {
        super();
    }
    /**
     * mnInsertarDatos: Este metodo se encarga de insetar datos en la tabla "MN_ARSENAL"
     * @param mnDato: Reprsenta al nombre del Arsenal.
     */
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
    /**
     * mnGetAll: Este metodo permite obtener la informacion de la base de datos y lo almacena en un ResultSet.
     */
    @Override
    public ResultSet mnGetAll() throws AppException {
        mnConsultaSQL= "SELECT NombreArsenal  FROM MN_ARSENAL";
        
        return mnGetResulSet(mnConsultaSQL); 
    }
}
