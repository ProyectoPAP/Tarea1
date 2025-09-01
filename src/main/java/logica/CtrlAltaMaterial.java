package logica;

import datatypes.*;
import java.util.Date;

public class CtrlAltaMaterial {
    
    private static CtrlAltaMaterial instancia = null;
    
    private CtrlAltaMaterial() {}
    
    public static CtrlAltaMaterial getInstancia() {
        if (instancia == null) {
            instancia = new CtrlAltaMaterial();
        }
        return instancia;
    }
    
    public void altaMaterial(String nombre, String descripcion, String tipo, int cantidad) {
        try {
            // Simulación de creación de material
            System.out.println("Creando material: " + nombre);
            System.out.println("Descripción: " + descripcion);
            System.out.println("Tipo: " + tipo);
            System.out.println("Cantidad: " + cantidad);
            System.out.println("Material creado exitosamente: " + nombre);
        } catch (Exception e) {
            System.out.println("Error al crear material: " + e.getMessage());
        }
    }
    
    public void altaLibro(String titulo, String autor, int cantPaginas, String isbn) {
        try {
            // Simulación de creación de libro
            System.out.println("Creando libro: " + titulo);
            System.out.println("Autor: " + autor);
            System.out.println("Páginas: " + cantPaginas);
            System.out.println("ISBN: " + isbn);
            System.out.println("Libro creado exitosamente: " + titulo);
        } catch (Exception e) {
            System.out.println("Error al crear libro: " + e.getMessage());
        }
    }
    
    public void altaDonacionMaterial(String nombre, String descripcion, String tipo) {
        try {
            // Simulación de donación de material
            System.out.println("Registrando donación de material: " + nombre);
            System.out.println("Descripción: " + descripcion);
            System.out.println("Tipo: " + tipo);
            System.out.println("Fecha de donación: " + new Date());
            System.out.println("Donación registrada exitosamente: " + nombre);
        } catch (Exception e) {
            System.out.println("Error al registrar donación: " + e.getMessage());
        }
    }
    
    public void altaDonacionLibro(String titulo, String autor, int cantPaginas) {
        try {
            // Simulación de donación de libro
            System.out.println("Registrando donación de libro: " + titulo);
            System.out.println("Autor: " + autor);
            System.out.println("Páginas: " + cantPaginas);
            System.out.println("Fecha de donación: " + new Date());
            System.out.println("Donación de libro registrada exitosamente: " + titulo);
        } catch (Exception e) {
            System.out.println("Error al registrar donación de libro: " + e.getMessage());
        }
    }
}
