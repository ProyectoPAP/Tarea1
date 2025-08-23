package interfaces;


import datatypes.DtLector;
import datatypes.DtPrestamo;

public interface ICtrlModPrest {
    public void modificarPrestamo(DtLector lector, DtPrestamo prestamoAModificar);
}
