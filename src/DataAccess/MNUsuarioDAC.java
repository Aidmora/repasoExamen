package DataAccess;

import java.sql.ResultSet;

import FrameWork.AppException;

public class MNUsuarioDAC  extends SQLiteDataHelper {

    public MNUsuarioDAC() throws AppException {
        super();
    }
    @Override
    public ResultSet mnGetAll() throws AppException {
        mnConsultaSQL= "SELECT IdUsuario, NombreUsuario, ContrasenaUsuario FROM MN_USUARIO";
        
        return mnGetResulSet(mnConsultaSQL);
    }
    

}
