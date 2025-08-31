package logica;

import datatypes.Zona;
import interfaces.ICtrlCambiarZonaLector;

public class CtrlCambiarZonaLector implements ICtrlCambiarZonaLector {
    
    public void cambiarZonaLector(String mail, Zona zona) {
        
        ManejadorUsuario mU = ManejadorUsuario.getInstancia();
        
        Usuario u = mU.buscarUsuario(mail);
        
        Lector l = (Lector) u;
        l.setZona(zona);
        mU.actualizarUsuario(u);
    }
}
