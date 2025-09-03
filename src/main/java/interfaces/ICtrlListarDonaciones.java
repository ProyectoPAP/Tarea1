package interfaces;

import java.util.Date;
import datatypes.DtMaterial;

public interface ICtrlListarDonaciones {
    public DtMaterial[] listarDonaciones();
    public DtMaterial[] listarDonacionesPorFecha(Date fechaIni, Date fechaFin);
}
