import interfaces;
import java.util.List;
import datatypes.DtPrestamo;
public interface ICtrlListarPrestamoPorZona {
    public List<DtPrestamo> listarPrestamoPorZona(String zona);
}
