package logica;

import java.util.ArrayList;
import java.util.Map;

import datatypes.DtMaterial;
import interfaces.ICtrlListarCantidadVecesPrestados;

public class CtrlListarCantidadVecesPrestados implements ICtrlListarCantidadVecesPrestados {
    
    @Override
    public ArrayList<Map.Entry<Integer, Material>> listarCantidadVecesPrestadoCompleto() {
        ManejadorMaterial mM = ManejadorMaterial.getInstancia();
        return mM.obtenerMaterialesMasPrestados();
    }
}