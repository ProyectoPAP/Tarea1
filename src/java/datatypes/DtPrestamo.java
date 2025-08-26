package datatypes;

public class DtPrestamo {
    private DtFecha fechaSolicitud;
    private DtFecha fechaDevolucion;
    private EstadoPrestamo estado;

    public DtPrestamo(DtFecha fechaSolicitud, DtFecha fechaDevolucion, EstadoPrestamo estado) {
        this.fechaSolicitud = fechaSolicitud;
        this.fechaDevolucion = fechaDevolucion;
        this.estado = estado;
    }

    public DtFecha getFechaSolicitud() {
        return fechaSolicitud;
    }

    public DtFecha getFechaDevolucion() {
        return fechaDevolucion;
    }

    public EstadoPrestamo getEstado() {
        return estado;
    }



}
