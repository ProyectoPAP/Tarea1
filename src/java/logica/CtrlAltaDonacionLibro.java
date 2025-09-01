package logica;
import interfaces.ICtrlAltaDonacionLibro;
import java.util.Date;
import net.bytebuddy.implementation.bind.annotation.Super;
public class CtrlAltaDonacionLibro implements ICtrlAltaDonacionLibro {
    public CtrlAltaDonacionLibro(){
        super();
    }
    
public void altaDonacionLibro(String titulo, int cantPag) {
    // Crear nueva instancia de Libro
    Libro nuevoLibro = new Libro(titulo, cantPag);

    // Obtener el manejador y agregar el nuevo libro
    Manejador manejador = Manejador.getInstance();
    manejador.agregarLibro(nuevoLibro);
}

}
