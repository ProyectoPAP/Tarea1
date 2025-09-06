package interfaces;

import datatypes.DtMaterial;
import java.util.ArrayList;
import java.util.Map;
import logica.Material;

public interface ICtrlListarCantidadVecesPrestados {
    public ArrayList<Map.Entry<Integer, Material>> listarCantidadVecesPrestadoCompleto();
}
