package DataAccess;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import FrameWork.AppExceptionAriel;

public class LibroFullDTO extends SQLiteDataHelper {

    public  ResultSet getAllData() throws AppExceptionAriel{
        String query = "SELECT ra.Resena AS Comentario, lib.NombreLibro AS Libro, au.NombreAutor AS Autor " +
                       "FROM RESENA ra " +
                       "JOIN LIBRO lib ON lib.IdLibro = ra.IdLibro " +
                       "JOIN AUTOR au ON au.IdAutor = ra.IdAutor " ;
         try{
            Connection connection = openConnection();
             Statement stmt = connection.createStatement();
             ResultSet resultSet = stmt.executeQuery(query);
             return resultSet;
         }catch(SQLException e) {
            throw new AppExceptionAriel(e, getClass(),"getAllData()");
        }
    }

}
