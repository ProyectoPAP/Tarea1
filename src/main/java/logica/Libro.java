package logica;

import javax.persistence.Entity;
import javax.persistence.DiscriminatorValue;
import java.util.Date;

@Entity
@DiscriminatorValue("LIB")
public class Libro extends Material{
    private String titulo;
    private String cantidadPaginas;

    // Constructor por defecto requerido por JPA
    public Libro() {
        super();
    }

    public Libro(String id, Date fechaIngreso, String titulo, String cantidadPaginas) {
        super(id, fechaIngreso);
        this.titulo = titulo;
        this.cantidadPaginas = cantidadPaginas;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getCantidadPaginas() {
        return cantidadPaginas;
    }

    public void setCantidadPaginas(String cantidadPaginas) {
        this.cantidadPaginas = cantidadPaginas;
    }

    @Override
    public String getTipo() {
        return "Libro";
    }

    @Override
    public String toString() {
        return "Libro{" +
                "id='" + getId() + '\'' +
                ", titulo='" + titulo + '\'' +
                ", cantidadPaginas='" + cantidadPaginas + '\'' +
                '}';
    }
}
