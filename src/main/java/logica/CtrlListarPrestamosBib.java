package logica;

import java.util.ArrayList;

import datatypes.DtBibliotecario;
import datatypes.DtPrestamo;

public class CtrlListarPrestamosBib {
    private static CtrlListarPrestamosBib instancia = null;

    private CtrlListarPrestamosBib(){}

    public static CtrlListarPrestamosBib getInstancia(){
        if(instancia == null){
            instancia = new CtrlListarPrestamosBib();
        }
        return instancia;
    }

    public ArrayList<DtPrestamo> listarPrestamoGestionado(DtBibliotecario bibliotecario){
        return ManejadorPrestamos.getInstancia().listarPrestamosBib(bibliotecario);
    }
}
