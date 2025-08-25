package datatypes;

import java.util.Date;

public class DtPrestamo {
    private String idMaterial;
    private String nombreLector;
    private String idBibliotecario;
    private Date fechaSolicitud;
    private Date fechaDevolucion;
    private EstadoPrestamo estado;

    public DtPrestamo(String idMaterial, String nombreLector, String idBibliotecario, Date fechaSolicitud, Date fechaDevolucion, EstadoPrestamo estado) {
        this.idMaterial = idMaterial;
        this.nombreLector = nombreLector;
        this.idBibliotecario = idBibliotecario;
        this.fechaSolicitud = fechaSolicitud;
        this.fechaDevolucion = fechaDevolucion;
        this.estado = estado;
    }

    public String getIdMaterial() {
        return idMaterial;
    }

    public String getNombreLector() {
        return nombreLector;
    }

    public String getIdBibliotecario() {
        return idBibliotecario;
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
