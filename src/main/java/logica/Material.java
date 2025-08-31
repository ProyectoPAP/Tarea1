package logica;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Id;

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
public abstract class Material {
    @Id
    private String id;
    private Date fechaIngreso;
    
    @OneToMany(mappedBy="material", cascade=CascadeType.ALL, orphanRemoval=true)
    private List<Prestamo> prestamos = new ArrayList<>();

    // Constructor por defecto requerido por JPA
    public Material() {
    }

    public Material(String id, Date fechaIngreso) {
        this.id = id;
        this.fechaIngreso = fechaIngreso;
    }

    public String getId() {
        return id;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Prestamo> getPrestamos() {
        return prestamos;
    }

    public void setPrestamos(List<Prestamo> prestamos) {
        this.prestamos = prestamos;
    }

    public void addPrestamo(Prestamo prestamo) {
        prestamos.add(prestamo);
        prestamo.setMaterial(this);
    }

    public void removePrestamo(Prestamo prestamo) {
        prestamos.remove(prestamo);
        prestamo.setMaterial(null);
    }


    public abstract String getTipo();
}
