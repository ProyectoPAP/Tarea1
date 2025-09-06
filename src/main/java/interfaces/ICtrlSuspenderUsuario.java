package interfaces;

import datatypes.DtUsuario;
import datatypes.EstadoLector;

public interface ICtrlSuspenderUsuario {
    public void suspenderUsuario(String emailUsuario, EstadoLector nuevoEstado);
    public DtUsuario[] obtenerUsuarios();
}
