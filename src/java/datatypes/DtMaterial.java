package datatypes;

public class DtMaterial {
    private int id;
    private DtFecha fechaIngreso;

    public DtMaterial(int id, DtFecha fechaIngreso) {
        super();
        this.id = id;
        this.fechaIngreso = fechaIngreso;
    }

    public int getId() {
        return id;
    }

    public DtFecha getFechaIngreso() {
        return fechaIngreso;
    }

}
