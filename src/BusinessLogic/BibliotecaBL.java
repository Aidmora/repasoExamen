package BusinessLogic;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import BusinessLogic.Entities.Biblioteca;
import DataAccess.LibroFullDTO;
import FrameWork.AppExceptionAriel;

public class BibliotecaBL {
    public List<Biblioteca> getAllData()throws AppExceptionAriel{
        try {
            LibroFullDTO lb= new LibroFullDTO();
            List<Biblioteca> lstBiblio= new ArrayList<Biblioteca>();
            ResultSet rs= lb.getAllData();
            while (rs.next()) {
                Biblioteca bl= new Biblioteca(rs.getString("Comentario"),
                                              rs.getString("Libro"), 
                                              rs.getString("Autor"));
                
               lstBiblio.add(bl); 
            }
            return lstBiblio;
        } catch (Exception e) {
            throw new AppExceptionAriel(e, getClass(),"getAllData");
        }
    }
}
