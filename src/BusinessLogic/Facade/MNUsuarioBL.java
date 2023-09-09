package BusinessLogic.Facade;

import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import BusinessLogic.Entities.MNUsuario;
import DataAccess.MNUsuarioDAC;
import FrameWork.AppExceptionAriel;

public class MNUsuarioBL {
    protected MNUsuarioDAC mnUsuarioDAC;

    public MNUsuarioBL() throws AppExceptionAriel {
        mnUsuarioDAC = new MNUsuarioDAC();
    }
    
    public List<MNUsuario> mnGetAll() throws AppExceptionAriel {
        ResultSet mnRs = mnUsuarioDAC.mnGetAll();
        List<MNUsuario> mnListaUsuarios = new ArrayList<>();
        MNUsuario mnUsuario;
        try {
            while (mnRs.next()) {
                mnUsuario = new MNUsuario();
                mnUsuario.setId(mnRs.getInt(1));
                mnUsuario.setNombreUsuario(mnRs.getString(2));
                mnUsuario.setClaveUsuario(mnRs.getString(3));   
                mnListaUsuarios.add(mnUsuario);
            }
                mnRs.close();
                return mnListaUsuarios;
        } catch (SQLException e) {
            throw new AppExceptionAriel(e, getClass(), "mnGetAll()");
        }
    }
}
