package interfaces;

import logica.altaDonacionLibro;
import logica.CtrlAltaDonacionLibro;
import logica.CtrlAltaDonacionMaterial;
import logica.CtrlAltaUsuario;
import logica.CtrlCambiarZonaLector;
import logica.CtrlListarDonaciones;
import logica.CtrlListarPrestamoActivoPorLector;
import logica.CtrlSuspenderLector;
import logica.CtrlAltaPrestamo;
import logica.CtrlListarPrestamosPorZona;
import logica.CtrlModificarPrestamo;
import logica.CtrlModificarEstadoPrestamo;
import logica.CtrlListarPrestamoBibliotecario;
import logica.CtrlListarTopPrestados;


public class Fabrica {
    private static Fabrica instancia = null;

    private Fabrica(){}

    public static Fabrica getInstancia() {
        if (instancia == null)
            instancia = new Fabrica();
        return instancia;
    }

    public ICtrlAltaDonacionLibro getCtrlAltaDonacionLibro(){
        return new ctrlAltaDonacionLibro();
    }

    public ICtrlAltaDonacionMaterial getCtrlAltaDonacionMaterial(){
        return new ctrlAltaDonacionMaterial();
    }
    
    public ICtrlAltaUsuario getCtrlAltaUsuario(){
        return new ctrlAltaUsuario();
    }

    public ICtrlCambiarZonaLector getCtrlCambiarZonaLector() {
        return new ctrlCambiarZonaLector();
    }

    public ICtrlListarDonaciones getCtrlListarDonaciones() {
        return new ctrlListarDonaciones();
    }

    public ICtrlListarPrestamoActivoPorLector getCtrlListarPrestamoActivoPorLector() {
        return new ctrlListarPrestamoActivoPorLector();
    }

    public ICtrlSuspenderLector getCtrlSuspenderLector() {
        return new ctrlSuspenderLector();
    }

    public ICtrlAltaPrestamo getCtrlAltaPrestamo() {
        return new ctrlAltaPrestamo();
    }

    public ICtrlListarPrestamosPorZona getCtrlListarPrestamosPorZona() {
        return new ctrlListarPrestamosPorZona();
    }

    public ICtrlModificarPrestamo getCtrlModificarPrestamo() {
        return new ctrlModificarPrestamo();
    }

    public ICtrlModificarEstadoPrestamo getCtrlModificarEstadoPrestamo() {
        return new ctrlModificarEstadoPrestamo();
    }

    public ICtrlListarPrestamoBibliotecario getCtrlListarPrestamoBibliotecario() {
        return new ctrlListarPrestamoBibliotecario();
    }

    public ICtrlListarTopPrestados getCtrlListarTopPrestados() {
        return new ctrlListarTopPrestados();
    }



}