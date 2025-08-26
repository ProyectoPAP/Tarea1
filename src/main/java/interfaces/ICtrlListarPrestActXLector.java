package interfaces;
//No olvidar hacer los import de las excepciones

import java.util.Set;

import datatypes.DtPrestamo;

public interface ICtrlListarPrestActXLector {
    public Set<DtPrestamo> listarPreActXLector(String correo);
}
