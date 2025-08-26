package interfaces;
//No olvidar hacer los import de las excepciones

import java.util.ArrayList;
import datatypes.DtFecha;
import datatypes.DtMaterial;

public interface ICtrlListarDonaciones {
    public ArrayList<DtMaterial> listarDonacionesEnUnRango(DtFecha fechaDesde, DtFecha fechaHasta);
}
