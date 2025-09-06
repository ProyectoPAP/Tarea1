package logica;

import interfaces.ICtrlSuspenderUsuario;
import datatypes.EstadoLector;
import datatypes.DtUsuario;
import java.util.ArrayList;

public class CtrlSuspenderUsuario implements ICtrlSuspenderUsuario {
    
    public CtrlSuspenderUsuario() {
        super();
    }
    
    @Override
    public void suspenderUsuario(String emailUsuario, EstadoLector nuevoEstado) {
        ManejadorUsuario mU = ManejadorUsuario.getInstancia();
        mU.cambiarEstadoUsuario(emailUsuario, nuevoEstado);
    }
    
    @Override
    public DtUsuario[] obtenerUsuarios() {
        ManejadorUsuario mU = ManejadorUsuario.getInstancia();
        return mU.obtenerLectores();
    }
}
