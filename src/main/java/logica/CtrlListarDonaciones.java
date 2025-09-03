package logica;

import java.util.ArrayList;
import java.util.Date;

import datatypes.DtMaterial;
import interfaces.ICtrlListarDonaciones;

public class CtrlListarDonaciones implements ICtrlListarDonaciones{
    
    @Override
    public DtMaterial[] listarDonaciones() {
        ArrayList<Material> materiales;
        ManejadorMaterial mM = ManejadorMaterial.getInstancia();
        materiales = mM.obtenerMaterialesCompletos();
        DtMaterial[] materiales_ret = new DtMaterial[materiales.size()];
        int i=0;
        for (Material material : materiales) {
            materiales_ret[i] = material.getDtMaterial();
            i++;
        }
        return materiales_ret;
    }

    @Override
    public DtMaterial[] listarDonacionesPorFecha(Date fechaIni, Date fechaFin) {
        ArrayList<Material> materiales;
        ManejadorMaterial mM = ManejadorMaterial.getInstancia();
        materiales = mM.obtenerMaterialesCompletosPorFecha(fechaIni, fechaFin);
        DtMaterial[] materiales_ret = new DtMaterial[materiales.size()];
        int i=0;
        for (Material material : materiales) {
            materiales_ret[i] = material.getDtMaterial();
            i++;
        }
        return materiales_ret;
    }
}


