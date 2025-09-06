package datatypes;

public class DtBibliotecario extends DtUsuario {
    private String numeroEmpleado;

    public DtBibliotecario(String nombre, String email, String numeroEmpleado) {
        super(nombre, email);
        this.numeroEmpleado = numeroEmpleado;
    }

    public String getNumeroEmpleado() {
        return numeroEmpleado;
    }
    
    @Override
    public String toString() {
        return "DtBibliotecario [numeroEmpleado=" + numeroEmpleado + "]";
    }
    
    
    
}
