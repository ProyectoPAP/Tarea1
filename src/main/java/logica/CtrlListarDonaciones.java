package logica;

import java.util.ArrayList;
import java.util.Date;

import datatypes.DtMaterial;
import interfaces.ICtrlListarDonaciones;

public class CtrlListarDonaciones implements ICtrlListarDonaciones{
    
    @Override
    public String[] listarDonaciones() {
        ArrayList<String> materiales;
        ManejadorMaterial mM = ManejadorMaterial.getInstancia();
        materiales = mM.obtenerMateriales();
        String [] materiales_ret = new String[materiales.size()];
        int i=0;
        for (String material : materiales) {
            materiales_ret[i] = material;
            i++;
        }
        return materiales_ret;
    }

    @Override
    public String[] listarDonacionesPorFecha(Date fechaIni, Date fechaFin) {
        ArrayList<String> materiales;
        ManejadorMaterial mM = ManejadorMaterial.getInstancia();
        materiales = mM.obtenerMaterialesPorFecha(fechaIni, fechaFin);
        String [] materiales_ret = new String[materiales.size()];
        int i=0;
        for (String material : materiales) {
            materiales_ret[i] = material;
            i++;
        }
        return materiales_ret;
    }
}


