package logica;

import java.util.ArrayList;

import datatypes.DtMaterial;

public class CtrlListarTopPrestados {
    private static CtrlListarTopPrestados instancia = null;

    private CtrlListarTopPrestados(){}

    public static CtrlListarTopPrestados getInstancia(){
        if(instancia == null){
            instancia = new CtrlListarTopPrestados();
        }
        return instancia;
    }

    public ArrayList<DtMaterial> listarTopPrestados(){
        return ManejadorMaterial.getInstancia().listarTopPrestados();
    }
}
