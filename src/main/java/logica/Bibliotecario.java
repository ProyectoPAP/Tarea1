package logica;

import javax.persistence.Entity;
import javax.persistence.DiscriminatorValue;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;
import java.util.List;

import datatypes.DtUsuario;
import datatypes.DtBibliotecario;

@Entity
@DiscriminatorValue("bibliotecario")
public class Bibliotecario extends Usuario {
    private String numeroEmpleado;

    @OneToMany(mappedBy = "bibliotecario",cascade=CascadeType.ALL,orphanRemoval=true)
    private List<Prestamo> prestamos;

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

    public List<Prestamo> getPrestamos() {
        return prestamos;
    }

    public void setPrestamos(Prestamo prestamo) {
        this.prestamos.add(prestamo);
    }

    @Override
    public DtUsuario getDtUsuario() {
        return new DtBibliotecario(getNombre(), getEmail(), numeroEmpleado);
    }
}
