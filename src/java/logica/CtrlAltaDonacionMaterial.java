package logica;
import interfaces.ICtrlAltaDonacionMaterial;
import java.util.Date;
import net.bytebuddy.implementation.bind.annotation.Super;

public class CtrlAltaDonacionMaterial implements ICtrlAltaDonacionMaterial {

    public CtrlAltaDonacionMaterial(){
        super();
    }
    public void altaDonacionMaterial(int id, Date fechaIngreso) {
        DonacionMaterial nuevaDonacion = new DonacionMaterial(id, fechaIngreso);
        Manejador manejador = Manejador.getInstance();
        manejador.agregarDonacionMaterial(nuevaDonacion);
    }
}
