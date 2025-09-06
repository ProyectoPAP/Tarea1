package interfaces;
//No olvidar hacer los import de las excepciones

import java.util.List;

import datatypes.DtPrestamo;

public interface ICtrlListarPrestActXLector {
    public List<DtPrestamo> listarPreActXLector(String correo);
}

