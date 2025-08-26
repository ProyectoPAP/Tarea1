package interfaces;

import java.util.ArrayList;
import java.util.Date;

import datatypes.DtPrestamo;
import datatypes.DtBibliotecario;

public interface ICtrlListarPrestamoBib {
    public ArrayList<DtPrestamo> listarPrestamoGestionado(DtBibliotecario bibliotecario);
}

