package logica;

import interfaces.ICtrlListarPrestamosBibliotecario;

import datatypes.DtPrestamo;

public class CtrlListarPrestamosBibliotecario implements ICtrlListarPrestamosBibliotecario {
    
    @Override
    public DtPrestamo[] listarPrestamos(String email) {
        ManejadorUsuario mU = ManejadorUsuario.getInstancia();
        return mU.obtenerPrestamos(email);
    }
}
