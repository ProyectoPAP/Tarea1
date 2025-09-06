package logica;

import interfaces.ICtrlListarPrestActXLector;
import java.util.List;
import java.util.ArrayList;
import datatypes.DtPrestamo;
import datatypes.DtLector;
import datatypes.DtBibliotecario;
import logica.ManejadorPrestamo;

public class CtrlListarPrestActXLector implements ICtrlListarPrestActXLector {
    
    public List<DtPrestamo> listarPreActXLector(String correo) {
        // El controlador solo coordina
        ManejadorPrestamo mP = ManejadorPrestamo.getInstancia();
        List<Prestamo> prestamosActivos = mP.obtenerPrestamosActivos(correo);
        
        // Convertir a DTOs para la presentación
        List<DtPrestamo> dtPrestamos = new ArrayList<>();
        for(Prestamo p : prestamosActivos) {
            dtPrestamos.add(new DtPrestamo(p.getMaterial().getDtMaterial(), (DtLector) p.getLector().getDtUsuario(), 

                                          (DtBibliotecario) p.getBibliotecario().getDtUsuario(), p.getFechaPrestamo(), 

                                          p.getFechaDevolucion(), p.getEstado()));
        }
        
        return dtPrestamos;
    }
}