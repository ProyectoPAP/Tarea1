package interfaces;

import datatypes.DtPrestamo;

public interface ICtrlListarPrestamosBibliotecario {
    public DtPrestamo[] listarPrestamos(String email);
}
