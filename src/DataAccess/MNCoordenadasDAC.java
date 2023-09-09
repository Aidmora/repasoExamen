package DataAccess;

import java.sql.ResultSet;
import java.sql.SQLException;

import FrameWork.AppException;

public class MNCoordenadasDAC extends SQLiteDataHelper {
    public MNCoordenadasDAC() throws AppException {
        super();
    }
    /**
     * mnInsertarDatos: Este metodo se encarga de insertar informacion en la base de datos, especificamente
     * en la talba "MN_COORDENADAS"
     * @param mnDato: Representa el valor numerico de las coordenadas.
     */
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
    /**
     * mnGetAll: Este metodo permite obtener la informacion de la base de datos y lo almacena en un ResultSet.
     */
    @Override
    public ResultSet mnGetAll() throws AppException {
        mnConsultaSQL= "SELECT Coordenada  FROM MN_COORDENADAS";
        
        return mnGetResulSet(mnConsultaSQL);    
    }
}
