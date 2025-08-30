package logica;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import persistencia.Conexion;
import datatypes.DtMaterial;

public class ManejadorMaterial {
    private static ManejadorMaterial instancia = null;

    private ManejadorMaterial(){}

    public static ManejadorMaterial getInstancia(){
        if(instancia == null){
            instancia = new ManejadorMaterial();
        }
        return instancia;
    }

    public void agregarMaterial(Material material){
        Conexion.getInstancia().getEntityManager().persist(material);
    }

    public void eliminarMaterial(Material material){
        Conexion.getInstancia().getEntityManager().remove(material);
    }

    public void actualizarMaterial(Material material){
        Conexion.getInstancia().getEntityManager().merge(material);
    }

    public Material buscarMaterial(String id){
        return Conexion.getInstancia().getEntityManager().find(Material.class, id);
    }

    public List<Material> obtenerMateriales(){
        return Conexion.getInstancia().getEntityManager().createQuery("SELECT m FROM Material m", Material.class).getResultList();
    }

    public List<Material> obtenerMaterialesEntreFechas(Date fechaInicio, Date fechaFin){
        return Conexion.getInstancia().getEntityManager().createQuery("SELECT m FROM Material m WHERE m.fechaIngreso BETWEEN :fechaInicio AND :fechaFin", Material.class)
        .setParameter("fechaInicio", fechaInicio)
        .setParameter("fechaFin", fechaFin)
        .getResultList();
    }


    public ArrayList<DtMaterial> listarTopPrestados(){
        EntityManager em = Conexion.getInstancia().getEntityManager();
        
        // Query to get only material ID and loan count, ordered by count descending
        Query query = em.createQuery(
            "SELECT m.id, COUNT(p) as prestamosCount FROM Material m " +
            "LEFT JOIN m.prestamos p " +
            "GROUP BY m.id " +
            "ORDER BY prestamosCount DESC", Object[].class);
        
        List<Object[]> results = query.getResultList();
        
        ArrayList<DtMaterial> dtMateriales = new ArrayList<>();
        for(Object[] result : results) {
            String idMaterial = (String) result[0];
            Long count = (Long) result[1];
            
            // Create DtMaterial with only ID and count info
            DtMaterial dtMaterial = new DtMaterial(idMaterial, null, "Prestamos: " + count);
            dtMateriales.add(dtMaterial);
        }
        
        return dtMateriales;
    }
}
