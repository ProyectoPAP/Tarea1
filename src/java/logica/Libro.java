package logica;

import datatypes.DtFecha;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Libro {

    @Id
    private String titulo;
    private int cantPaginas;
    @OneToMany(mappedBy = "libro", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Prestamo> prestamos = new ArrayList<>();

    // Constructor
    public Libro(String titulo, int cantPaginas) {
        this.titulo=titulo;
        this.cantPaginas=cantPaginas;
        this.prestamos = new ArrayList<>();
    }
    
    // Constructor por defecto
    public Libro() {
        this.prestamos = new ArrayList<>();
    }
    
    // Getters y Setters
    public int getTitulo() {
        return titulo;
    }
    
    public void setTitulo(String titulo) {
        this.titulo=titulo;
    }
    
    public DtFecha getCantPaginas() {
        return cantPaginas;
    }
    
    public void setCantPaginas(int cantPaginas) {
        this.cantPaginas = cantPaginas;
    }

    
}