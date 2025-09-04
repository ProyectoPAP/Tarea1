package logica;

import datatypes.DtUsuario;
import datatypes.Zona;
import interfaces.ICtrlCambiarZonaLector;

public class CtrlCambiarZonaLector implements ICtrlCambiarZonaLector {
    
    @Override
    public void cambiarZonaLector(String emailLector, Zona zona) {
        ManejadorUsuario mU = ManejadorUsuario.getInstancia();
        mU.cambiarZona(emailLector, zona);
    }
    
    @Override
    public DtUsuario[] listarLectores() {
        ManejadorUsuario mU = ManejadorUsuario.getInstancia();
        return mU.obtenerLectores();
    }
}
