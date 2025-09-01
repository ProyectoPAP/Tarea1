package logica;

import datatypes.DtFecha;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Material {

    @Id
    private int id;
    private DtFecha fechaIngreso;
    
    @OneToMany(mappedBy = "material", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Prestamo> prestamos = new ArrayList<>();

    // Constructor
    public Material(int id, DtFecha fechaIngreso) {
        this.id = id;
        this.fechaIngreso = fechaIngreso;
        this.prestamos = new ArrayList<>();
    }
    
    // Constructor por defecto
    public Material() {
        this.prestamos = new ArrayList<>();
    }
    
    // Getters y Setters
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public DtFecha getFechaIngreso() {
        return fechaIngreso;
    }
    
    public void setFechaIngreso(DtFecha fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    
}