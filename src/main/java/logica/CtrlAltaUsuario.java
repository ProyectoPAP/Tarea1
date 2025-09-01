package logica;

import java.util.Date;
import datatypes.*;

public class CtrlAltaUsuario {
    
    private static CtrlAltaUsuario instancia = null;
    private String nombre;
    private String correo;
    
    private CtrlAltaUsuario() {}
    
    public static CtrlAltaUsuario getInstancia() {
        if (instancia == null) {
            instancia = new CtrlAltaUsuario();
        }
        return instancia;
    }
    
    public void altaUsuario(String nombre, String correo, String tipo) {
        this.nombre = nombre;
        this.correo = correo;
        System.out.println("Usuario registrado: " + nombre + " (" + tipo + ")");
    }
    
    public void crearLector(String direccion, DtFecha fechaRegistro, EstadoLector estado, Zona zona) {
        try {
            // Simulación de creación de lector
            System.out.println("Creando lector: " + this.nombre);
            System.out.println("Dirección: " + direccion);
            System.out.println("Fecha registro: " + fechaRegistro.getDia() + "/" + fechaRegistro.getMes() + "/" + fechaRegistro.getAnio());
            System.out.println("Estado: " + estado);
            System.out.println("Zona: " + zona);
            System.out.println("Lector creado exitosamente: " + this.nombre);
        } catch (Exception e) {
            System.out.println("Error al crear lector: " + e.getMessage());
        }
    }
    
    public void crearBibliotecario(int numEmpleado, Zona zona) {
        try {
            // Simulación de creación de bibliotecario
            System.out.println("Creando bibliotecario: " + this.nombre);
            System.out.println("Número empleado: " + numEmpleado);
            System.out.println("Zona: " + zona);
            System.out.println("Bibliotecario creado exitosamente: " + this.nombre);
        } catch (Exception e) {
            System.out.println("Error al crear bibliotecario: " + e.getMessage());
        }
    }
}
