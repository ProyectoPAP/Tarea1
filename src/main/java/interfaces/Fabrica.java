package interfaces;

import logica.CtrlListarDonaciones;
import logica.CtrlListarDonacionesEntreFechas;
import logica.CtrlListarTopPrestados;
import logica.CtrlModEstPrest;
import logica.CtrlModPrest;

    public class Fabrica {
    private static Fabrica instance = null;

    private Fabrica(){}

    public static Fabrica getInstance(){
        if(instance == null){
            instance = new Fabrica();
        }
        return instance;
    }

    public ICtrlListarDonacionesEntreFechas getCtrlListarDonacionesEntreFechas(){
        return new CtrlListarDonacionesEntreFechas();
    }

    public ICtrlListarDonaciones getCtrlListarDonaciones(){
        return new CtrlListarDonaciones();
    }

    public ICtrlListarTopPrestados getCtrlListarTopPrestados(){
        return new CtrlListarTopPrestados();
    }

    public ICtrlModEstPrest getCtrlModEstPrest(){
        return new CtrlModEstPrest();
    }

    public ICtrlModPrest getCtrlModPrest(){
        return new CtrlModPrest();
    }

}
