package logica;

import java.util.ArrayList;

import datatypes.DtMaterial;
import interfaces.ICtrlListarTopPrestados;

public class CtrlListarTopPrestados implements ICtrlListarTopPrestados {
    
    public ArrayList<DtMaterial> listarTopPrestados(){
        return ManejadorMaterial.getInstancia().listarTopPrestados();
    }
}
