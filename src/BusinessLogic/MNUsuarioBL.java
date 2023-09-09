package BusinessLogic;

import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import BusinessLogic.Entities.MNUsuario;
import DataAccess.MNUsuarioDAC;
import FrameWork.AppException;

public class MNUsuarioBL {
    protected MNUsuarioDAC mnUsuarioDAC;

    public MNUsuarioBL() throws AppException {
        mnUsuarioDAC = new MNUsuarioDAC();
    }
    /**
     * mnGetAll: Este metodo se encarga de insertar la informacion obtenida por el DAC,  en una lista 
     * correspondiente a Usuario. 
     * @return List<MNUsuario>: Representa a una lista de Usuarios.
     * @throws AppException
     */
    public List<MNUsuario> mnGetAll() throws AppException {
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
            throw new AppException(e, getClass(), "mnGetAll()");
        }
    }
}
