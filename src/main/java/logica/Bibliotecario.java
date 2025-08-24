package logica;

import javax.persistence.Entity;
import javax.persistence.DiscriminatorValue;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;
import java.util.List;
import java.util.ArrayList;
import logica.Prestamo;

@Entity
@DiscriminatorValue("B")
public class Bibliotecario extends Usuario{
    private String numeroEmpleado;

    @OneToMany(mappedBy="bibliotecario", cascade=CascadeType.ALL, orphanRemoval=true)
    private List<Prestamo> prestamos = new ArrayList<>();

    // Constructor por defecto requerido por JPA
    public Bibliotecario() {
        super();
    }

    public Bibliotecario(String nombre, String email, String numeroEmpleado) {
        super(nombre, email);
        this.numeroEmpleado = numeroEmpleado;
    }

    public String getNumeroEmpleado() {
        return numeroEmpleado;
    }

    public void setNumeroEmpleado(String numeroEmpleado) {
        this.numeroEmpleado = numeroEmpleado;
    }

    public List<Prestamo> getPrestamos() {
        return prestamos;
    }

    public void setPrestamos(List<Prestamo> prestamos) {
        this.prestamos = prestamos;
    }

    @Override
    public String toString() {
        return "Nombre: " + getNombre() + "\nEmail: " + getEmail() + "\nNumero de Empleado: " + numeroEmpleado;
    } 

    public void addPrestamo(Prestamo prestamo) {
        prestamos.add(prestamo);
        prestamo.setBibliotecario(this);
    }

    public void removePrestamo(Prestamo prestamo) {
        prestamos.remove(prestamo);
        prestamo.setBibliotecario(null);
    }
}
