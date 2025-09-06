package logica;

import datatypes.DtPrestamo;
import datatypes.Zona;
import interfaces.ICtrlListarPrestamosPorZona;
import java.util.ArrayList;

public class CtrlListarPrestamosPorZona implements ICtrlListarPrestamosPorZona {
    
    @Override
    public DtPrestamo[] listarPrestamosPorZona(Zona zona) {
        ManejadorPrestamo mP = ManejadorPrestamo.getInstancia();
        ArrayList<Prestamo> prestamos = mP.obtenerPrestamosPorZona(zona);
        DtPrestamo[] prestamosRet = new DtPrestamo[prestamos.size()];
        int i = 0;
        for (Prestamo prestamo : prestamos) {
            prestamosRet[i] = prestamo.getDtPrestamo();
            i++;
        }
        return prestamosRet;
    }
}
