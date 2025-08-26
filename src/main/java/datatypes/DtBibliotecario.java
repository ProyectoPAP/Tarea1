package datatypes;

public class DtBibliotecario {
    private String nombre;
    private String email;
    private String numeroEmpleado;

    public DtBibliotecario(String nombre, String email, String numeroEmpleado) {
        this.nombre = nombre;
        this.email = email;
        this.numeroEmpleado = numeroEmpleado;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEmail() {
        return email;
    }

    public String getNumeroEmpleado() {
        return numeroEmpleado;
    }

    @Override
    public String toString() {
        return "Nombre: " + nombre + "\nEmail: " + email + "\nNumero de Empleado: " + numeroEmpleado;
    }
}
