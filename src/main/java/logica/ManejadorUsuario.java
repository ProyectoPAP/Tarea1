package logica;

import datatypes.EstadoLector;
import datatypes.Zona;
import persistencia.Conexion;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import java.util.ArrayList;
import datatypes.DtPrestamo;
import datatypes.EstadoPrestamo;

public class ManejadorUsuario {
    private static ManejadorUsuario instancia = null;

    private ManejadorUsuario() {
        super();
    }

    public static ManejadorUsuario getInstancia() {
        if (instancia == null) {
            instancia = new ManejadorUsuario();
        }
        return instancia;
    }

    public void altaUsuario(Usuario usr) {
        Conexion conexion = Conexion.getInstancia();
        EntityManager em = conexion.getEntityManager();
        em.getTransaction().begin();
        em.persist(usr);
        em.getTransaction().commit();
    }

    public Usuario buscarUsuario(String email) {
        Conexion conexion = Conexion.getInstancia();
        EntityManager em = conexion.getEntityManager();
        
        Usuario usr = em.find(Usuario.class, email);
        return usr;
    }

    public ArrayList<String> obtenerUsuarios() {
        Conexion conexion = Conexion.getInstancia();
        EntityManager em = conexion.getEntityManager();

        Query query = em.createQuery("SELECT u FROM Usuario u");

        List<Usuario> listUsr = (List<Usuario>) query.getResultList();

        ArrayList<String> aRetornar = new ArrayList<>();
        for (Usuario usr : listUsr) {
            aRetornar.add(usr.getEmail());
        }
        return aRetornar;
    }

    public void suspenderUsuario(String email) {
        Conexion conexion = Conexion.getInstancia();
        EntityManager em = conexion.getEntityManager();

        Usuario usr = em.find(Usuario.class, email);
        ((Lector) usr).setEstado(EstadoLector.SUSPENDIDO);
        em.getTransaction().begin();
        em.merge(usr);
        em.getTransaction().commit();
    }

    public void cambiarZona(String email, Zona zona) {
        Conexion conexion = Conexion.getInstancia();
        EntityManager em = conexion.getEntityManager();

        Usuario usr = em.find(Usuario.class, email);
        ((Lector) usr).setZona(zona);
        em.getTransaction().begin();
        em.merge(usr);
        em.getTransaction().commit();
    }

    public ArrayList<DtPrestamo> getPrestamosActivos(String email) {
        Conexion conexion = Conexion.getInstancia();
        EntityManager em = conexion.getEntityManager();
        
        Usuario usr = em.find(Usuario.class, email);
        if (usr instanceof Lector) {
            ArrayList<DtPrestamo> aRetornar = new ArrayList<>();
            for (Prestamo prestamo : ((Lector) usr).getPrestamos()) {
                if (prestamo.getEstado() == EstadoPrestamo.EN_CURSO) {
                    aRetornar.add(prestamo.getDtPrestamo());
                }
            }
            return aRetornar;
        } else {
            return null;
        }
        
    }
    
}
