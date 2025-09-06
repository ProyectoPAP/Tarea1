package logica;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;
import java.util.List;

import datatypes.DtMaterial;
import logica.Prestamo;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Material {
    @Id
    private String id;
    private Date fechaIngreso;

    @OneToMany(mappedBy = "material",cascade=CascadeType.ALL,orphanRemoval=true)
    private List<Prestamo> prestamos;

    public Material() {
        super();
    }

    public Material(String id, Date fechaIngreso) {
        super();
        this.id = id;
        this.fechaIngreso = fechaIngreso;
    }

    public String getId() {
        return id;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }
    
    public List<Prestamo> getPrestamos() {
        return prestamos;
    }

    public void setPrestamos(Prestamo prestamo) {
        this.prestamos.add(prestamo);
    }

    public abstract DtMaterial getDtMaterial();

    @Override
    public String toString() {
        return "Material [id=" + id + ", fechaIngreso=" + fechaIngreso + "]";
    }


}
