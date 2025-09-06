package interfaces;

import datatypes.DtUsuario;
import datatypes.Zona;

public interface ICtrlCambiarZonaLector {
    public void cambiarZonaLector(String emailLector, Zona zona);
    
    public DtUsuario[] listarLectores();

}
