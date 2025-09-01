package logica;

import interfaces.ICtrlAltaMaterial;
import datatypes.DtMaterial;
import datatypes.DtArticulo;
import datatypes.DtLibro;

public class CtrlAltaMaterial implements ICtrlAltaMaterial{
    
    @Override
    public void altaMaterial(DtMaterial material) {
        ManejadorMaterial mM = ManejadorMaterial.getInstancia();
        Material nuevoMaterial = mM.buscarMaterial(material.getId());
        if (nuevoMaterial != null) {
            throw new RuntimeException("El material ya existe");
        }
        if (material instanceof DtArticulo) {
            nuevoMaterial = new Articulo(material.getId(), material.getFechaIngreso(), ((DtArticulo) material).getDescripcion(), ((DtArticulo) material).getPesoKg(), ((DtArticulo) material).getDimensiones());
        } else {
            nuevoMaterial = new Libro(material.getId(), material.getFechaIngreso(), ((DtLibro) material).getTitulo(), ((DtLibro) material).getCantidadPaginas());
        }
        mM.altaMaterial(nuevoMaterial);
    }
}
