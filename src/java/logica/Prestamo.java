package logica;

import datatypes.DtFecha;
import datatypes.EstadoPrestamo;


public class Prestamo {
    private DtFecha fechaSolicitud;
    private DtFecha fechaDevolucion;
    private EstadoPrestamo estado;

    public Prestamo(DtFecha fechaSolicitud, DtFecha fechaDevolucion, EstadoPrestamo estado) {
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
