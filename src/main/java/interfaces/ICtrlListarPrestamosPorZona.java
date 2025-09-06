package interfaces;

import datatypes.DtPrestamo;
import datatypes.Zona;

public interface ICtrlListarPrestamosPorZona {
    public DtPrestamo[] listarPrestamosPorZona(Zona zona);
}
