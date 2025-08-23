package datatypes;

public class DtLector {
    private String nombre;
    private String email;
    private String direccion;
    private Date fechaRegistro;
    private EstadoLector estado;
    private Zona zona;

    public DtLector(String nombre, String email, String direccion, Date fechaRegistro, EstadoLector estado, Zona zona) {
        this.nombre = nombre;
        this.email = email;
        this.direccion = direccion;
        this.fechaRegistro = fechaRegistro;
        this.estado = estado;
        this.zona = zona;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEmail() {
        return email;
    }

    public String getDireccion() {
        return direccion;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public EstadoLector getEstado() {
        return estado;
    }

    public Zona getZona() {
        return zona;
    }

    @Override
    public String toString() {
        return "Nombre: " + nombre + "\nEmail: " + email + "\nDireccion: " + direccion + "\nFecha de Registro: " + fechaRegistro + "\nEstado: " + estado + "\nZona: " + zona;
    }
}
