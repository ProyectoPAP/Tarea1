package logica;

import interfaces.ICtrlListarPrestActXLector;
import java.util.List;
import java.util.ArrayList;
import datatypes.DtPrestamo;
import logica.ManejadorUsuario;

public class CtrlListarPreActLector implements ICtrlListarPrestActXLector {
    
    public List<DtPrestamo> listarPreActXLector(String correo) {
        // El controlador solo coordina
        ManejadorPrestamos mP = ManejadorPrestamos.getInstancia();
        List<Prestamo> prestamosActivos = mP.obtenerPrestamosActivos(correo);
        
        // Convertir a DTOs para la presentación
        List<DtPrestamo> dtPrestamos = new ArrayList<>();
        for(Prestamo p : prestamosActivos) {
            dtPrestamos.add(new DtPrestamo(p.getIdMaterial(), p.getNombreLector(), 

                                          p.getIdBibliotecario(), p.getFechaSolicitud(), 

                                          p.getFechaDevolucion(), p.getEstado()));
        }
        
        return dtPrestamos;
    }
}
