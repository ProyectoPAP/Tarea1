package logica;

import interfaces.ICtrlAltaPrestamo;
import datatypes.DtPrestamo;
import logica.Prestamo;
import logica.ManejadorPrestamos;


public class CtrlAltaPrestamo implements ICtrlAltaPrestamo {
    public void altaPrestamo(DtPrestamo prestamo) {
        Prestamo prestamo = new Prestamo(prestamo.getFechaSolicitud(), prestamo.getFechaDevolucion(), prestamo.getEstado(), prestamo.getIdMaterial(), prestamo.getNombreLector(), prestamo.getIdBibliotecario());
        ManejadorPrestamos.getInstancia().agregarPrestamo(prestamo);
    }
}
