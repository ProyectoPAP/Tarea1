package logica;

import interfaces.ICtrlAltaPrestamo;
import datatypes.EstadoLector;
import datatypes.EstadoPrestamo;
import java.util.Date;

public class CtrlAltaPrestamo implements ICtrlAltaPrestamo {
    
    @Override
    public void altaPrestamo(String emailLector, String emailBibliotecario, String idMaterial) {
        ManejadorUsuario mU = ManejadorUsuario.getInstancia();
        ManejadorMaterial mM = ManejadorMaterial.getInstancia();
        
        // Buscar el lector
        Usuario lector = mU.buscarUsuario(emailLector);
        if (lector == null || !(lector instanceof Lector)) {
            throw new RuntimeException("El lector no existe o no es válido");
        }

        if (((Lector) lector).getEstado() == EstadoLector.SUSPENDIDO) {
            throw new RuntimeException("El lector está suspendido");
        }
        
        // Buscar el bibliotecario
        Usuario bibliotecario = mU.buscarUsuario(emailBibliotecario);
        if (bibliotecario == null || !(bibliotecario instanceof Bibliotecario)) {
            throw new RuntimeException("El bibliotecario no existe o no es válido");
        }
        
        // Buscar el material
        Material material = mM.buscarMaterial(idMaterial);
        if (material == null) {
            throw new RuntimeException("El material no existe");
        }
        
        // Verificar si ya existe un préstamo con la misma clave compuesta
        ManejadorPrestamo mP = ManejadorPrestamo.getInstancia();
        Prestamo prestamoExistente = mP.buscarPrestamoPorParametros(emailLector, emailBibliotecario, idMaterial);
        if (prestamoExistente != null) {
            throw new RuntimeException("Ya existe un préstamo con estos parámetros (lector: " + emailLector + 
                                     ", bibliotecario: " + emailBibliotecario + ", material: " + idMaterial + ")");
        }
        
        // Crear el préstamo
        Date fechaPrestamo = new Date();
        Date fechaDevolucion = null; // Se establecerá cuando se devuelva
        EstadoPrestamo estado = EstadoPrestamo.EN_CURSO;
        
        Prestamo prestamo = new Prestamo((Lector) lector, (Bibliotecario) bibliotecario, 
                                        material, fechaPrestamo, fechaDevolucion, estado);
        
        // Persistir el préstamo
        mP.altaPrestamo(prestamo);
    }
}
