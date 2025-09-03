package logica;

import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.Entity;
import javax.persistence.IdClass;
import javax.persistence.Enumerated;
import javax.persistence.EnumType;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

import datatypes.DtPrestamo;
import datatypes.DtLector;
import datatypes.DtBibliotecario;
import datatypes.EstadoPrestamo;
import persistencia.PrestamoID;

@Entity
@IdClass(PrestamoID.class)
public class Prestamo {
    @Id
    @ManyToOne
    @JoinColumn(
        insertable = false,
        updatable = false
    )
    private Lector lector;

    @Id
    @ManyToOne
    @JoinColumn(
        insertable = false,
        updatable = false
    )
    private Bibliotecario bibliotecario;

    @Id
    @ManyToOne
    @JoinColumn(
        insertable = false,
        updatable = false
    )
    private Material material;

    @Temporal(TemporalType.DATE)
    private Date fechaPrestamo;
    @Temporal(TemporalType.DATE)
    private Date fechaDevolucion;
    @Enumerated(EnumType.STRING)
    private EstadoPrestamo estado;

    public Prestamo() {
        super();
    }

    public Prestamo(Lector lector, Bibliotecario bibliotecario, Material material, Date fechaPrestamo, Date fechaDevolucion, EstadoPrestamo estado) {
        super();
        this.lector = lector;
        this.bibliotecario = bibliotecario;
        this.material = material;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucion = fechaDevolucion;
        this.estado = estado;
    }

    public Lector getLector() {
        return lector;
    }

    public Bibliotecario getBibliotecario() {
        return bibliotecario;
    }

    public Material getMaterial() {
        return material;
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

    public void setLector(Lector lector) {
        this.lector = lector;
    }

    public void setBibliotecario(Bibliotecario bibliotecario) {
        this.bibliotecario = bibliotecario;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public void setFechaPrestamo(Date fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }

    public void setFechaDevolucion(Date fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

    public void setEstado(EstadoPrestamo estado) {
        this.estado = estado;
    }

    public DtPrestamo getDtPrestamo() {
        return new DtPrestamo(material.getDtMaterial(), (DtLector) lector.getDtUsuario(), (DtBibliotecario) bibliotecario.getDtUsuario(), fechaPrestamo, fechaDevolucion, estado);
    }

    @Override
    public String toString() {
        return "Prestamo [lector=" + lector + ", bibliotecario=" + bibliotecario + ", material=" + material + ", fechaPrestamo=" + fechaPrestamo + ", fechaDevolucion=" + fechaDevolucion + ", estado=" + estado + "]";
    }
}
