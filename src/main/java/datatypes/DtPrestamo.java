package datatypes;

import java.util.Date;

public class DtPrestamo {
    private DtMaterial material;
    private DtLector lector;
    private DtBibliotecario bibliotecario;
    private Date fechaPrestamo;
    private Date fechaDevolucion;
    private EstadoPrestamo estado;

    public DtPrestamo(DtMaterial material, DtLector lector, DtBibliotecario bibliotecario, Date fechaPrestamo, Date fechaDevolucion, EstadoPrestamo estado) {
        this.material = material;
        this.lector = lector;
        this.bibliotecario = bibliotecario;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucion = fechaDevolucion;
        this.estado = estado;
    }

    public DtMaterial getMaterial() {
        return material;
    }

    public DtLector getLector() {
        return lector;
    }

    public DtBibliotecario getBibliotecario() {
        return bibliotecario;
    }

    public Date getFechaPrestamo() {
        return fechaPrestamo;
    }
    public Date getFechaDevolucion() {
        return fechaDevolucion;
    }
    public EstadoPrestamo getEstado() {
        return estado;
    }
    @Override
    public String toString() {
        return "DtPrestamo [material=" + material + ", lector=" + lector + ", bibliotecario=" + bibliotecario + ", material=" + material + ", fechaPrestamo=" + fechaPrestamo + ", fechaDevolucion=" + fechaDevolucion + ", estado=" + estado + "]";
    }

}
