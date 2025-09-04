package interfaces;

import logica.CtrlAltaUsr;
import logica.CtrlAltaMaterial;
import logica.CtrlListarDonaciones;
import logica.Prestamo;
import logica.Material;
import logica.Lector;
import logica.Bibliotecario;
import logica.Articulo;
import logica.Libro;
import logica.CtrlAltaPrestamo;
import logica.CtrlListarPrestamos;
import logica.CtrlListarPreActLector;

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
    
    public ICtrlAltaMaterial getCtrlAltaMaterial() {
        return new CtrlAltaMaterial();
    }
    
    public ICtrlListarDonaciones getCtrlListarDonaciones() {
        return new CtrlListarDonaciones();
    }

    public ICtrlAltaPrestamo getCtrlAltaPrestamo() {
        return new CtrlAltaPrestamo();
    }

    public ICtrlListarPrestamos getCtrlListarPrestamos() {
        return new CtrlListarPrestamos();
    }

    public ICtrlListarPrestActXLector getCtrlListarPreActLector() {
        return new CtrlListarPreActLector();
    }
}
