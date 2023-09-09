package DataAccess;

import java.sql.ResultSet;

import FrameWork.AppExceptionAriel;

public class MNUsuarioDAC  extends SQLiteDataHelper {

    public MNUsuarioDAC() throws AppExceptionAriel {
        super();
    }
    @Override
    public ResultSet mnGetAll() throws AppExceptionAriel {
        mnConsultaSQL= "SELECT IdUsuario, NombreUsuario, ContrasenaUsuario FROM MN_USUARIO";
        
        return mnGetResulSet(mnConsultaSQL);
    }
    

}
