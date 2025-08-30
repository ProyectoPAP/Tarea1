package logica;


import datatypes.DtPrestamo;
import datatypes.EstadoPrestamo;
import interfaces.ICtrlModEstPrest;

public class CtrlModEstPrest implements ICtrlModEstPrest {

    public void modificarEstadoPrestamo(DtPrestamo prestamo, EstadoPrestamo estado){
        Prestamo prestamoModificar = ManejadorPrestamos.getInstancia().buscarPrestamo(prestamo.getIdMaterial(), prestamo.getNombreLector(), prestamo.getIdBibliotecario());
        prestamoModificar.setEstado(estado);
        ManejadorPrestamos.getInstancia().actualizarPrestamo(prestamoModificar);
    }
}
