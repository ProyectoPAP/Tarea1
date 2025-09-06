package logica;

import persistencia.Conexion;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;

public class ManejadorMaterial {
    private static ManejadorMaterial instancia = null;

    private ManejadorMaterial() {
        super();
    }

    public static ManejadorMaterial getInstancia() {
        if (instancia == null) {
            instancia = new ManejadorMaterial();
        }
        return instancia;
    }

    public void altaMaterial(Material material) {
        Conexion conexion = Conexion.getInstancia();
        EntityManager em = conexion.getEntityManager();
        em.getTransaction().begin();
        em.persist(material);
        em.getTransaction().commit();
    }

    public Material buscarMaterial(String id) {
        Conexion conexion = Conexion.getInstancia();
        EntityManager em = conexion.getEntityManager();
        
        Material material = em.find(Material.class, id);
        return material;
    }

    public ArrayList<String> obtenerMateriales() {
        Conexion conexion = Conexion.getInstancia();
        EntityManager em = conexion.getEntityManager();

        Query query = em.createQuery("SELECT m FROM Material m");

        List<Material> listMaterial = (List<Material>) query.getResultList();

        ArrayList<String> aRetornar = new ArrayList<>();
        for (Material material : listMaterial) {
            aRetornar.add(material.getId());
        }
        return aRetornar;
    }

    public ArrayList<Material> obtenerMaterialesCompletos() {
        Conexion conexion = Conexion.getInstancia();
        EntityManager em = conexion.getEntityManager();

        Query query = em.createQuery("SELECT m FROM Material m");

        List<Material> listMaterial = (List<Material>) query.getResultList();

        ArrayList<Material> aRetornar = new ArrayList<>();
        for (Material material : listMaterial) {
            aRetornar.add(material);
        }
        return aRetornar;
    }

    public ArrayList<String> obtenerMaterialesPorFecha(Date fechaIni, Date fechaFin) {
        Conexion conexion = Conexion.getInstancia();
        EntityManager em = conexion.getEntityManager();

        Query query = em.createQuery("SELECT m FROM Material m WHERE m.fechaIngreso BETWEEN :fechaIni AND :fechaFin");
        query.setParameter("fechaIni", fechaIni);
        query.setParameter("fechaFin", fechaFin);

        List<Material> listMaterial = (List<Material>) query.getResultList();

        ArrayList<String> aRetornar = new ArrayList<>();
        for (Material material : listMaterial) {
            aRetornar.add(material.getId());
        }
        return aRetornar;
    }

    public ArrayList<Material> obtenerMaterialesCompletosPorFecha(Date fechaIni, Date fechaFin) {
        Conexion conexion = Conexion.getInstancia();
        EntityManager em = conexion.getEntityManager();

        Query query = em.createQuery("SELECT m FROM Material m WHERE m.fechaIngreso BETWEEN :fechaIni AND :fechaFin");
        query.setParameter("fechaIni", fechaIni);
        query.setParameter("fechaFin", fechaFin);

        List<Material> listMaterial = (List<Material>) query.getResultList();

        ArrayList<Material> aRetornar = new ArrayList<>();
        for (Material material : listMaterial) {
            aRetornar.add(material);
        }
        return aRetornar;
    }
}
