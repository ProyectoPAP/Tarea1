package logica;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.DiscriminatorValue;

import datatypes.DtMaterial;
import datatypes.DtLibro;

@Entity
@DiscriminatorValue("libro")
public class Libro extends Material {
    private String titulo;
    private String cantidadPaginas;

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

    public String getCantidadPaginas() {
        return cantidadPaginas;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setCantidadPaginas(String cantidadPaginas) {
        this.cantidadPaginas = cantidadPaginas;
    }

    public DtMaterial getDtMaterial() {
        return new DtLibro(getId(), getFechaIngreso(), titulo, cantidadPaginas);
    }

    @Override
    public String toString() {
        return "Libro [titulo=" + titulo + ", cantidadPaginas=" + cantidadPaginas + "]";
    }
}
