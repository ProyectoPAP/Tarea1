package logica;

import java.util.Date;

import datatypes.DtMaterial;
import datatypes.DtArticulo;
import javax.persistence.Entity;
import javax.persistence.DiscriminatorValue;

@Entity
@DiscriminatorValue("articulo")
public class Articulo extends Material {
    private String descripcion;
    private float pesoKg;
    private String dimensiones;

    public Articulo() {
        super();
    }

    public Articulo(String id, Date fechaIngreso, String descripcion, float pesoKg, String dimensiones) {
        super(id, fechaIngreso);
        this.descripcion = descripcion;
        this.pesoKg = pesoKg;
        this.dimensiones = dimensiones;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public float getPesoKg() {
        return pesoKg;
    }

    public String getDimensiones() {
        return dimensiones;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setPesoKg(float pesoKg) {
        this.pesoKg = pesoKg;
    }

    public void setDimensiones(String dimensiones) {
        this.dimensiones = dimensiones;
    }

    public DtMaterial getDtMaterial() {
        return new DtArticulo(getId(), getFechaIngreso(), descripcion, pesoKg, dimensiones);
    }

    @Override
    public String toString() {
        return "Articulo [descripcion=" + descripcion + ", pesoKg=" + pesoKg + ", dimensiones=" + dimensiones + "]";
    }
}
