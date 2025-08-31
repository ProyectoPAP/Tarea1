package interfaces;


import datatypes.DtPrestamo;
import datatypes.DtLector;

public interface ICtrlModPrest {
    public void modificarPrestamo(DtLector lector ,DtPrestamo prestamoAModificar);
}
