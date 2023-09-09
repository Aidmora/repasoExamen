package DataAccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.sql.Statement;
import FrameWork.AppExceptionAriel;

public abstract class SQLiteDataHelper {
    private static String DBPathConnection = "jdbc:sqlite:dataBase\\prueba.db"; //URL de la conexion a la base de datos.
    private static Connection conn = null; 
    public SQLiteDataHelper(){
        
    } 
    public static synchronized Connection openConnection() throws AppExceptionAriel{
        try {
            if(conn == null)
                conn = DriverManager.getConnection(DBPathConnection);
            System.out.println("La conexión con la base de datos se ha establecido.");
        } catch (SQLException e) {
            throw new AppExceptionAriel(e,"SQLiteDataHelper","Fallo la conexion a la base de datos");
        } 
        return conn; 
    }
    protected static void closeConnection() throws AppExceptionAriel{
        try {
            if (conn != null)
                conn.close();
        } catch (Exception e) {
            throw new AppExceptionAriel(e,"SQLiteDataHelper", "Fallo la conección con la base de datos");
        }
    }
    public static void crearTablaDesdeCSV(List<String> lines, String nombreTabla)throws AppExceptionAriel, SQLException{
        if (lines !=null && !lines.isEmpty()) {
            Connection connection= openConnection();
            Statement stmt=  connection.createStatement();
            String encabezado= lines.get(0);
            String [] columNames= encabezado.split(";");
            StringBuilder crearTablaSql= new StringBuilder("CREATE TABLE ");
            crearTablaSql.append(nombreTabla).append(" (");
            for (int i = 0; i < columNames.length; i++) {
                String camposTabla= columNames[i].trim();
                if(camposTabla.toLowerCase().contains("id")){
                    if(i==0){
                       crearTablaSql.append(camposTabla).append(" INTEGER  NOT NULL PRIMARY KEY , "); 
                    }else if(i>0)
                        crearTablaSql.append(camposTabla).append(" INTEGER NOT NULL, ");
                }else if (!(camposTabla.toLowerCase().contains("id"))){
                    if (camposTabla.toLowerCase().equals("estado")){
                        crearTablaSql.append(camposTabla).append(" TEXT  DEFAULT 'A' , ");
                    }else if(!(camposTabla.toLowerCase().equals("estado"))){
                        crearTablaSql.append(camposTabla).append(" TEXT NOT NULL, ");
                    }
                    
                }
            }
            if(nombreTabla.toLowerCase().equals("libro")){
                crearTablaSql.append(" CONSTRAINT FK_IdAutor, ");
                crearTablaSql.append(" FOREIGN KEY (IdAutor) REFERENCES AUTOR(IdAutor), ");
            }
            if(nombreTabla.toLowerCase().equals("resena")){
                crearTablaSql.append(" CONSTRAINT FK_IdLibro, ");
                crearTablaSql.append(" FOREIGN KEY (IdLibro) REFERENCES LIBRO(IdLibro), "); 
                crearTablaSql.append(" CONSTRAINT FK_IdAutor, ");
                crearTablaSql.append(" FOREIGN KEY (IdAutor) REFERENCES AUTOR(IdAutor), ");                                
            }
            crearTablaSql.setLength(crearTablaSql.length() - 2);
            crearTablaSql.append(");");
            stmt.executeUpdate(crearTablaSql.toString());
            System.out.println("Se creo la tabla con exito");
            if(nombreTabla.toLowerCase().equals("autor")){
                insertarDatosAutor(lines, "AUTOR", connection);
            }
            if(nombreTabla.toLowerCase().equals("libro")){
                insertarDatosLibro(lines, "LIBRO", connection);  
            }
            if(nombreTabla.toLowerCase().equals("resena")){
                insertarDatosResena(lines, "RESENA", connection);
            }
        }
        
    }
    public static void insertarDatosAutor(List<String> lines, String nombreTabla, Connection connection){
        String insertSQL= "INSERT INTO " + nombreTabla + " (IdAutor,NombreAutor,Nacionalidad,FechaNacimiento,Estado) " +
        "VALUES ( ?, ?, ?, ?, ?)";
         try (PreparedStatement pstmt = connection.prepareStatement(insertSQL)) {
            for (String line : lines) {
                String[] values = line.split(";");
                if(values[0].toLowerCase().trim().contains("id")){
                    continue;
                }
                if (values.length == 5) { 
                    pstmt.setInt(1,Integer.parseInt(values[0].trim()) );
                    pstmt.setString(2,values[1].trim() );
                    pstmt.setString(3,values[2].trim() );
                    pstmt.setString(4,values[3].trim() );
                    pstmt.setString(5,values[4].trim() );
                    pstmt.executeUpdate(); 
                } else {
                    System.err.println("La línea no tiene suficientes valores: " + line);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void insertarDatosLibro(List<String> lines, String nombreTabla, Connection connection){
        String insertSQL = "INSERT INTO "+nombreTabla+" (IdLibro, IdAutor, NombreLibro, FechaPublicacion, Genero, Editorial, Estado) " +
        "VALUES ( ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(insertSQL)) {
            for (String line : lines) {
                String[] values = line.split(";");
                if(values[0].toLowerCase().trim().contains("id")){
                    continue;
                }
                if (values.length <= 7) {
                    pstmt.setInt(1, Integer.parseInt(values[0].trim()));
                    pstmt.setInt(2, Integer.parseInt(values[1].trim()));
                    pstmt.setString(3, values[2].trim());
                    pstmt.setString(4, values[3].trim());
                    pstmt.setString(5, values[4].trim());
                    pstmt.setString(6, values[5].trim());
                    pstmt.setString(7, values[6].trim());
                    pstmt.executeUpdate();
                } else {
                    System.err.println("La línea no tiene suficientes valores: " + line);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    } 
    public static void insertarDatosResena(List<String> lines, String nombreTabla, Connection connection){
        String insertSQL = "INSERT INTO "+nombreTabla+" (IdResena, IdLibro, IdAutor, Resena, Puntuacion, FechaResena, Estado) " +
        "VALUES ( ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(insertSQL)) {
        for (String line : lines) {
            String[] values = line.split(";");
            if(values[0].toLowerCase().trim().contains("id") || values[0].trim().isEmpty() ){
                continue;
            }
            if (values.length <= 7) { 
                pstmt.setInt(1, Integer.parseInt(values[0].trim()));
                pstmt.setInt(2, Integer.parseInt(values[1].trim()));
                pstmt.setInt(3, Integer.parseInt(values[2].trim()));
                pstmt.setString(4, values[3].trim());
                pstmt.setString(5, values[4].trim());
                pstmt.setString(6, values[5].trim());
                pstmt.setString(7, values[6].trim());
                pstmt.executeUpdate();
            } else {
                System.err.println("La línea no tiene suficientes valores: " + line);
            }
        }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}