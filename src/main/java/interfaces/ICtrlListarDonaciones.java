package interfaces;

import java.util.Date;

public interface ICtrlListarDonaciones {
    public String[] listarDonaciones();
    public String[] listarDonacionesPorFecha(Date fechaIni, Date fechaFin);
}
