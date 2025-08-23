package interfaces;

import java.util.ArrayList;
import java.util.Date;

import datatypes.DtPrestamo;
import datatypes.EstadoPrestamo;


public interface ICtrlModEstPrest {
    public void modificarEstadoPrestamo(DtPrestamo prestamo, EstadoPrestamo estado);
}
