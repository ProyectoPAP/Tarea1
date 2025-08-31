package logica;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import datatypes.DtMaterial;
import interfaces.ICtrlListarDonacionesEntreFechas;


public class CtrlListarDonacionesEntreFechas implements ICtrlListarDonacionesEntreFechas {

    public List<DtMaterial> listarDonacionesEntreFechas(Date fechaInicio, Date fechaFin) {
        ManejadorMaterial mM = ManejadorMaterial.getInstancia();
        List<Material> materiales = mM.obtenerMaterialesEntreFechas(fechaInicio, fechaFin);
        List<DtMaterial> dtMateriales = new ArrayList<>();
        for (Material material : materiales) {
            dtMateriales.add(new DtMaterial(material.getId(), material.getFechaIngreso(), material.getTipo()));
        }
        return dtMateriales;
    }
}
