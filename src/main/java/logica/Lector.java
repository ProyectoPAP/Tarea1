package logica;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.DiscriminatorValue;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;

import datatypes.EstadoLector;
import datatypes.DtUsuario;
import datatypes.DtLector;
import datatypes.Zona;
import logica.Prestamo;
import java.util.List;

@Entity
@DiscriminatorValue("lector")
public class Lector extends Usuario {
    private String direccion;
    private Date fechaRegistro;
    private EstadoLector estado;
    private Zona zona;

    @OneToMany(mappedBy = "lector",cascade=CascadeType.ALL,orphanRemoval=true)
    private List<Prestamo> prestamos;
    
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

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public EstadoLector getEstado() {
        return estado;
    }

    public Zona getZona() {
        return zona;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public void setEstado(EstadoLector estado) {
        this.estado = estado;
    }

    public void setZona(Zona zona) {
        this.zona = zona;
    }

    @Override
    public DtUsuario getDtUsuario() {
        return new DtLector(getNombre(), getEmail(), direccion, fechaRegistro, estado, zona);
    }

    public List<Prestamo> getPrestamos() {
        return prestamos;
    }

    public void setPrestamos(Prestamo prestamo) {
        this.prestamos.add(prestamo);
    }



    @Override
    public String toString() {
        return "Lector [direccion=" + direccion + ", fechaRegistro=" + fechaRegistro + ", estado=" + estado + ", zona=" + zona + "]";
    }

}
