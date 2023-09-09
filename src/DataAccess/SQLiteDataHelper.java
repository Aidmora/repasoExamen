package DataAccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import FrameWork.AppException;

public abstract class SQLiteDataHelper {
    private  String DBPathConnection = "jdbc:sqlite:dataBase\\mnBaseDatos.db"; //URL de la conexion a la base de datos.
    protected static Connection mnConn = null;
    protected ResultSet mnRs;
    protected PreparedStatement mnPreparedSt; 
    protected String mnConsultaSQL;
    protected Statement mnSt;
    public SQLiteDataHelper() throws AppException{
        mnConn=openConnection();
        mnRs=null;
        mnPreparedSt=null;
        mnConsultaSQL=null;
        mnSt=null;
    } 
    public  synchronized Connection openConnection() throws AppException{
        try {
            if(mnConn == null)
                mnConn = DriverManager.getConnection(DBPathConnection);
        } catch (SQLException e) {
            throw new AppException(e,"SQLiteDataHelper","Fallo la conexion a la base de datos");
        } 
        return mnConn; 
    }
    protected  void closeConnection() throws AppException{
        try {
            if (mnConn != null)
                mnConn.close();
        } catch (Exception e) {
            throw new AppException(e,"SQLiteDataHelper", "Fallo la conección con la base de datos");
        }
    }
    protected ResultSet mnGetResulSet(String mnConsultaSQL) throws AppException{
        try {
            mnConn=openConnection();
            mnSt= mnConn.createStatement();
            mnRs= mnSt.executeQuery(mnConsultaSQL);
            
        } catch (SQLException e) {

        }
        return mnRs;
            
    }
    public abstract ResultSet mnGetAll()  throws AppException;
    }
