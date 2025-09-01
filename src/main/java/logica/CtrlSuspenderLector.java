package logica;

import interfaces.ICtrlSuspenderLector;
import datatypes.EstadoLector;
import datatypes.DtLector;
import logica.Usuario;
import logica.Lector;

public class CtrlSuspenderLector implements ICtrlSuspenderLector {
    public void suspenderLector(DtLector lector) {
        Usuario usuario = ManejadorUsuario.getInstancia().buscarUsuario(lector.getEmail());
        
        // Verificar que el usuario existe
        if (usuario == null) {
            throw new IllegalArgumentException("No se encontró un usuario con el email: " + lector.getEmail());
        }
        
        // Verificar que el usuario es efectivamente un Lector
        if (!(usuario instanceof Lector)) {
            throw new IllegalArgumentException("El usuario con email " + lector.getEmail() + " no es un Lector. No se puede suspender un Bibliotecario.");
        }
        
        Lector lectorModificar = (Lector) usuario;
        lectorModificar.modificarEstado(EstadoLector.SUSPENDIDO);
        ManejadorUsuario.getInstancia().actualizarUsuario(lectorModificar);
    }
}