package datatypes;

public class DtPrestamo {
    private Date fechaSolicitud;
    private Date fechaDevolucion;
    private EstadoPrestamo estado;

    public DtPrestamo(Date fechaSolicitud, Date fechaDevolucion, EstadoPrestamo estado) {
        this.fechaSolicitud = fechaSolicitud;
        this.fechaDevolucion = fechaDevolucion;
        this.estado = estado;
    }

    public Date getFechaSolicitud() {
        return fechaSolicitud;
    }

    public Date getFechaDevolucion() {
        return fechaDevolucion;
    }

    public EstadoPrestamo getEstado() {
        return estado;
    }

    @Override
    public String toString() {
        return "Fecha de Solicitud: " + fechaSolicitud + "\nFecha de Devolucion: " + fechaDevolucion + "\nEstado: " + estado;
    }
}
