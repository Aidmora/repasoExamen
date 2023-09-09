package BusinessLogic.Entities;

public class Biblioteca {
    String nombreLibro;
    String nombreAutor; 
    String reseña;
    public Biblioteca (String nombreReseña,String nomLibro, String nomAutor){
        this.reseña=nombreReseña;
        this.nombreLibro=nomLibro;
        this.nombreAutor=nomAutor;
    }
    public String getNombreLibro() {
        return nombreLibro;
    }
    public void setNombreLibro(String nombreLibro) {
        this.nombreLibro = nombreLibro;
    }
    public String getNombreAutor() {
        return nombreAutor;
    }
    public void setNombreAutor(String nombreAutor) {
        this.nombreAutor = nombreAutor;
    }
    public String getReseña() {
        return reseña;
    }
    public void setReseña(String reseña) {
        this.reseña = reseña;
    }

}
