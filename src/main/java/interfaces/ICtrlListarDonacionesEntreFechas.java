package interfaces;

import java.util.Date;
import java.util.List;
import datatypes.DtMaterial;

public interface ICtrlListarDonacionesEntreFechas {
    List<DtMaterial> listarDonacionesEntreFechas(Date fechaInicio, Date fechaFin);
}
