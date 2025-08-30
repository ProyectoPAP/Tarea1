package logica;

import datatypes.DtPrestamo;
import datatypes.DtLector;
import interfaces.ICtrlModPrest;

public class CtrlModPrest implements ICtrlModPrest {;

    public void modificarPrestamo(DtLector lector, DtPrestamo prestamoAModificar){
        Prestamo prestamoModificar = ManejadorPrestamos.getInstancia().buscarPrestamo(prestamoAModificar.getIdMaterial(), prestamoAModificar.getNombreLector(), prestamoAModificar.getIdBibliotecario());
        ManejadorPrestamos.getInstancia().actualizarPrestamo(prestamoModificar);
    }
}
