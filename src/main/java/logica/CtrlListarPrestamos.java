package logica;

import interfaces.ICtrlListarPrestamos;
import datatypes.DtPrestamo;
import java.util.ArrayList;

public class CtrlListarPrestamos implements ICtrlListarPrestamos {
    
    @Override
    public DtPrestamo[] listarPrestamos() {
        ManejadorPrestamo mP = ManejadorPrestamo.getInstancia();
        ArrayList<Prestamo> prestamos = mP.obtenerPrestamos();
        
        DtPrestamo[] prestamosRet = new DtPrestamo[prestamos.size()];
        int i = 0;
        for (Prestamo prestamo : prestamos) {
            prestamosRet[i] = prestamo.getDtPrestamo();
            i++;
        }
        return prestamosRet;
    }
}
