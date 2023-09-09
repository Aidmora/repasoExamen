package DataAccess;

import java.sql.ResultSet;
import java.sql.SQLException;

import FrameWork.AppException;

public class MNArsenalTipoDAC extends SQLiteDataHelper {
    public MNArsenalTipoDAC() throws AppException {
        super();
    }
    /**
     * mnInsertarDatos: Este metodo se encarga de insertar la informacion en la base de datos, 
     * en este caso inserta la informacion en la tabla "MN_ARSENALTIPO"
     * @param mnDato: Representa al nombre del tipo de arsenal.
     */
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
    /**
     * mnGetAll: Este metodo permite obtener la informacion de la base de datos y lo almacena en un ResultSet.
     */
    @Override
    public ResultSet mnGetAll() throws AppException {
        mnConsultaSQL= "SELECT NombreArsenalTipo  FROM MN_ARSENALTIPO";
        
        return mnGetResulSet(mnConsultaSQL); 
    }
}
