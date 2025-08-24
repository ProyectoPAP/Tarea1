package logica;

import javax.persistence.Entity;
import javax.persistence.DiscriminatorValue;
import java.util.Date;

@Entity
@DiscriminatorValue("A")
public class Articulo extends Material {
    private String descripcion;
    private float pesoKg;
    private String dimensiones;

    // Constructor por defecto requerido por JPA
    public Articulo() {
        super();
    }

    public Articulo(String id, Date fechaIngreso, String descripcion, float pesoKg, String dimensiones) {
        super(id, fechaIngreso);
        this.descripcion = descripcion;
        this.pesoKg = pesoKg;
        this.dimensiones = dimensiones;
    }

    // Getters y setters
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public float getPesoKg() {
        return pesoKg;
    }

    public void setPesoKg(float pesoKg) {
        this.pesoKg = pesoKg;
    }

    public String getDimensiones() {
        return dimensiones;
    }

    public void setDimensiones(String dimensiones) {
        this.dimensiones = dimensiones;
    }

    @Override
    public String getTipo() {
        return "Articulo";
    }

    @Override
    public String toString() {
        return "Articulo{" +
                "id='" + getId() + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", pesoKg=" + pesoKg +
                ", dimensiones='" + dimensiones + '\'' +
                '}';
    }
}   
