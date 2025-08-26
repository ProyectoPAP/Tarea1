package datatypes;

import java.util.Date;

public class DtMaterial {
    private String id;
    private Date fechaIngreso;
    private String tipo;

    public DtMaterial(String id, Date fechaIngreso, String tipo) {
        this.id = id;
        this.fechaIngreso = fechaIngreso;
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }

    public String getId() {
        return id;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    @Override
    public String toString() {
        return "ID: " + id + "\nFecha de Ingreso: " + fechaIngreso;
    }
}
