package logica;

import javax.persistence.Entity;
import javax.persistence.DiscriminatorValue;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import datatypes.EstadoLector;
import datatypes.Zona;


@Entity
@DiscriminatorValue("L")
public class Lector extends Usuario{
    private String direccion;
    private Date fechaRegistro;
    private EstadoLector estado;
    private Zona zona;

    @OneToMany(mappedBy="lector", cascade=CascadeType.ALL, orphanRemoval=true)
    private List<Prestamo> prestamos = new ArrayList<>();

    // Constructor por defecto requerido por JPA
    public Lector() {
        super();
    }

    public Lector(String nombre, String email, String direccion, Date fechaRegistro, EstadoLector estado, Zona zona) {
        super(nombre, email);
        this.direccion = direccion;
        this.fechaRegistro = fechaRegistro;
        this.estado = estado;
        this.zona = zona;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public EstadoLector getEstado() {
        return estado;
    }

    public void setEstado(EstadoLector estado) {
        this.estado = estado;
    }

    public Zona getZona() {
        return zona;
    }

    public void setZona(Zona zona) {
        this.zona = zona;
    }

    public List<Prestamo> getPrestamos() {
        return prestamos;
    }

    public void setPrestamos(List<Prestamo> prestamos) {
        this.prestamos = prestamos;
    }
    public void modificarEstado(EstadoLector estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Nombre: " + getNombre() + "\nEmail: " + getEmail() + "\nDireccion: " + direccion + "\nFecha de Registro: " + fechaRegistro + "\nEstado: " + estado + "\nZona: " + zona;
    }

    public void addPrestamo(Prestamo prestamo) {
        prestamos.add(prestamo);
        prestamo.setLector(this);
    }

    public void removePrestamo(Prestamo prestamo) {
        prestamos.remove(prestamo);
        prestamo.setLector(null);
    }
}
