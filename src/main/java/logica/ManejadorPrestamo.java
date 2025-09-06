package logica;

import persistencia.Conexion;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import java.util.ArrayList;
import datatypes.DtPrestamo;
import datatypes.EstadoPrestamo;
import datatypes.Zona;

public class ManejadorPrestamo {
    private static ManejadorPrestamo instancia = null;

    private ManejadorPrestamo() {
        super();
    }

    public static ManejadorPrestamo getInstancia() {
        if (instancia == null) {
            instancia = new ManejadorPrestamo();
        }
        return instancia;
    }

    public void altaPrestamo(Prestamo prestamo) {
        Conexion conexion = Conexion.getInstancia();
        EntityManager em = conexion.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(prestamo);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        }
    }

    public Prestamo buscarPrestamo(String idMaterial) {
        Conexion conexion = Conexion.getInstancia();
        EntityManager em = conexion.getEntityManager();
        
        Query query = em.createQuery("SELECT p FROM Prestamo p WHERE p.material.id = :idMaterial AND p.estado = 'EN_CURSO'");
        query.setParameter("idMaterial", idMaterial);
        
        List<Prestamo> resultados = query.getResultList();
        if (!resultados.isEmpty()) {
            return resultados.get(0);
        }
        return null;
    }

    public ArrayList<Prestamo> obtenerPrestamos() {
        Conexion conexion = Conexion.getInstancia();
        EntityManager em = conexion.getEntityManager();
        
        Query query = em.createQuery("SELECT p FROM Prestamo p");
        List<Prestamo> prestamos = query.getResultList();
        
        ArrayList<Prestamo> aRetornar = new ArrayList<>();
        for (Prestamo prestamo : prestamos) {
            aRetornar.add(prestamo);
        }
        return aRetornar;
    }

    public ArrayList<Prestamo> obtenerPrestamosPorZona(Zona zona) {
        Conexion conexion = Conexion.getInstancia();
        EntityManager em = conexion.getEntityManager();
        
        Query query = em.createQuery("SELECT p FROM Prestamo p WHERE p.lector.zona = :zona");
        query.setParameter("zona", zona);
        List<Prestamo> prestamos = query.getResultList();

        ArrayList<Prestamo> aRetornar = new ArrayList<>();
        for (Prestamo prestamo : prestamos) {
            aRetornar.add(prestamo);
        }
        return aRetornar;
    }

    public Prestamo buscarPrestamoPorParametros(String emailLector, String emailBibliotecario, String idMaterial) {
        Conexion conexion = Conexion.getInstancia();
        EntityManager em = conexion.getEntityManager();
        
        Query query = em.createQuery("SELECT p FROM Prestamo p WHERE p.lector.email = :emailLector AND p.bibliotecario.email = :emailBibliotecario AND p.material.id = :idMaterial");
        query.setParameter("emailLector", emailLector);
        query.setParameter("emailBibliotecario", emailBibliotecario);
        query.setParameter("idMaterial", idMaterial);
        
        @SuppressWarnings("unchecked")
        List<Prestamo> resultados = query.getResultList();
        if (!resultados.isEmpty()) {
            return resultados.get(0);
        }
        return null;
    }

    public void actualizarPrestamo(Prestamo prestamo) {
        Conexion conexion = Conexion.getInstancia();
        EntityManager em = conexion.getEntityManager();
        em.getTransaction().begin();
        em.merge(prestamo);
        em.getTransaction().commit();
    }

    public ArrayList<Prestamo> obtenerPrestamosActivos(String emailLector) {
        Conexion conexion = Conexion.getInstancia();
        EntityManager em = conexion.getEntityManager();
        
        Query query = em.createQuery("SELECT p FROM Prestamo p WHERE p.lector.email = :emailLector and p.estado  = :estadoPrestamo or p.estado =: estPrestamo");
        query.setParameter("emailLector", emailLector);
        
        query.setParameter("estadoPrestamo", EstadoPrestamo.EN_CURSO);
        query.setParameter("estPrestamo", EstadoPrestamo.EN_CURSO);
        @SuppressWarnings("unchecked")
        List<Prestamo> prestamos = query.getResultList();
        
        ArrayList<Prestamo> aRetornar = new ArrayList<>();
        for (Prestamo prestamo : prestamos) {
            aRetornar.add(prestamo);
        }
        return aRetornar;
    }

}
