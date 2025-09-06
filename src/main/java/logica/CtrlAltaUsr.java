package logica;

import interfaces.ICtrlAltaUsr;
import datatypes.DtUsuario;
import datatypes.DtLector;
import datatypes.DtBibliotecario;

public class CtrlAltaUsr implements ICtrlAltaUsr{
    
    @Override
    public void altaUsuario(DtUsuario usr) {
        ManejadorUsuario mU = ManejadorUsuario.getInstancia();
        Usuario nuevoUsr = mU.buscarUsuario(usr.getEmail());
        if (nuevoUsr != null) {
            throw new RuntimeException("El usuario ya existe");
        }
        if (usr instanceof DtLector) {
            nuevoUsr = new Lector(usr.getNombre(), usr.getEmail(), ((DtLector) usr).getDireccion(), ((DtLector) usr).getFechaRegistro(), ((DtLector) usr).getEstado(), ((DtLector) usr).getZona());
        } else {
            nuevoUsr = new Bibliotecario(usr.getNombre(), usr.getEmail(), ((DtBibliotecario) usr).getNumeroEmpleado());
        }
        mU.altaUsuario(nuevoUsr);
    }
} 
