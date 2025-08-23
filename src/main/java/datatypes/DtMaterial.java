package datatypes;

public class DtMaterial {
    private String id;
    private Date fechaIngreso;

    public DtMaterial(String id, Date fechaIngreso) {
        this.id = id;
        this.fechaIngreso = fechaIngreso;
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
