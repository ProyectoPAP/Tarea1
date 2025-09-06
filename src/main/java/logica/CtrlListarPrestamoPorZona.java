package logica;

import interfaces.ICtrlListarPrestamoPorZona;
import datatypes.DtPrestamo;
import logica.Prestamo;
import logica.ManejadorPrestamos;
import java.util.List;
import java.util.ArrayList;


class CtrlListarPrestamoPorZona implements ICtrlListarPrestamoPorZona {
    public List<DtPrestamo> listarPrestamoPorZona(String zona) {
    List<Prestamo> prestamos = ManejadorPrestamos.getInstancia().listarPrestamoPorZona(zona);
    List<DtPrestamo> dtPrestamos = new ArrayList<>();
    for(Prestamo p : prestamos) {
        dtPrestamos.add(new DtPrestamo(p.getIdMaterial(), p.getNombreLector(), p.getIdBibliotecario(), p.getFechaSolicitud(), p.getFechaDevolucion(), p.getEstado()));
    }
    return dtPrestamos;
    }
}