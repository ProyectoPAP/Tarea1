package logica;

import persistencia.Conexion;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import java.util.ArrayList;
import datatypes.DtPrestamo;
import datatypes.EstadoPrestamo;

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
        em.getTransaction().begin();
        em.persist(prestamo);
        em.getTransaction().commit();
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

    public List<Prestamo> obtenerPrestamosActivos(String mail){ //verificar si esta bien
        // 1. Obtener el usuario por mail
        ManejadorUsuario mU = ManejadorUsuario.getInstancia();
        Usuario u = mU.buscarUsuario(mail);
        
        // 2. Hacer casting a Lector
        Lector l = (Lector) u;
        
        // 3. Obtener todos los préstamos del lector
        List<Prestamo> todosLosPrestamos = l.getPrestamos();
        
        // 4. Filtrar solo los activos (PENDIENTE o EN_CURSO)
        List<Prestamo> prestamosActivos = new ArrayList<>();
        for(Prestamo p : todosLosPrestamos) {
            if(p.getEstado() == EstadoPrestamo.PENDIENTE || 
               p.getEstado() == EstadoPrestamo.EN_CURSO) {
                prestamosActivos.add(p);
            }
        }
        
        return prestamosActivos;
    }
}

