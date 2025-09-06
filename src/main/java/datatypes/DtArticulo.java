package datatypes;

import java.util.Date;

public class DtArticulo extends DtMaterial {
    private String descripcion;
    private float pesoKg;
    private String dimensiones;

    public DtArticulo(String id, Date fechaIngreso, String descripcion, float pesoKg, String dimensiones) {
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

    @Override
    public String toString() {
        return "DtArticulo [descripcion=" + descripcion + ", pesoKg=" + pesoKg + ", dimensiones=" + dimensiones + "]";
    }
}
