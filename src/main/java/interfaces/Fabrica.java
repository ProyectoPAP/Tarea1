package interfaces;

import logica.CtrlAltaUsr;
import logica.Prestamo;
import logica.Material;
import logica.Lector;
import logica.Bibliotecario;
import logica.Articulo;
import logica.Libro;

public class Fabrica {
    private static Fabrica instancia = null;

    private Fabrica() {
        super();
    }

    public static Fabrica getInstancia() {
        if (instancia == null) {
            instancia = new Fabrica();
        }
        return instancia;
    }

    public ICtrlAltaUsr getCtrlAltaUsr() {
        return new CtrlAltaUsr();
    }
}
