package logica;

import datatypes.DtPrestamo;

public class CtrlModPrest {
    private static CtrlModPrest instancia = null;

    private CtrlModPrest(){}

    public static CtrlModPrest getInstancia(){
        if(instancia == null){
            instancia = new CtrlModPrest();
        }
        return instancia;
    }

    public void modificarPrestamo(DtPrestamo prestamoAModificar){
        Prestamo prestamoModificar = ManejadorPrestamos.getInstancia().buscarPrestamo(prestamoAModificar.getIdMaterial(), prestamoAModificar.getNombreLector(), prestamoAModificar.getIdBibliotecario());
        ManejadorPrestamos.getInstancia().actualizarPrestamo(prestamoModificar);
    }
}
