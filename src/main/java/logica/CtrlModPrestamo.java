package logica;
import interfaces.ICtrlModPrestamo;
import datatypes.DtPrestamo;

/*Como administrador, quiero actualizar el estado de un préstamo a “EN CURSO” o “DEVUELTO” para reflejar su progreso. */

    public class CtrlModPrestamo implements ICtrlModPrestamo {

    @Override
    public void modificarPrestamo( DtPrestamo prestamoAModificar) {
        ManejadorPrestamo mP = ManejadorPrestamo.getInstancia();
        
        // Buscar el préstamo específico usando los parámetros completos
        Prestamo prestamo = mP.buscarPrestamoPorParametros(
            prestamoAModificar.getLector().getEmail(),
            prestamoAModificar.getBibliotecario().getEmail(),
            prestamoAModificar.getMaterial().getId()
        );
        
        if (prestamo == null) {
            throw new RuntimeException("El préstamo no existe");
        }
        
        // Actualizar solo los campos que se pueden modificar
        prestamo.setEstado(prestamoAModificar.getEstado());
        prestamo.setFechaPrestamo(prestamoAModificar.getFechaPrestamo());
        prestamo.setFechaDevolucion(prestamoAModificar.getFechaDevolucion());
        
        mP.actualizarPrestamo(prestamo);
    }
    
    
}
