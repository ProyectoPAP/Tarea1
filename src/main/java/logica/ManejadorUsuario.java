package logica;

import datatypes.EstadoLector;
import datatypes.Zona;
import persistencia.Conexion;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import java.util.ArrayList;

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

    public Usuario buscarUsuario(String nombre) {
        Conexion conexion = Conexion.getInstancia();
        EntityManager em = conexion.getEntityManager();
        
        Usuario usr = em.find(Usuario.class, nombre);
        return usr;
    }

    public ArrayList<String> obtenerUsuarios() {
        Conexion conexion = Conexion.getInstancia();
        EntityManager em = conexion.getEntityManager();

        Query query = em.createQuery("SELECT u FROM Usuario u");

        List<Usuario> listUsr = (List<Usuario>) query.getResultList();

        ArrayList<String> aRetornar = new ArrayList<>();
        for (Usuario usr : listUsr) {
            aRetornar.add(usr.getNombre());
        }
        return aRetornar;
    }

    public void suspenderUsuario(String nombre) {
        Conexion conexion = Conexion.getInstancia();
        EntityManager em = conexion.getEntityManager();

        Usuario usr = em.find(Usuario.class, nombre);
        ((Lector) usr).setEstado(EstadoLector.SUSPENDIDO);
        em.getTransaction().begin();
        em.merge(usr);
        em.getTransaction().commit();
    }

    public void cambiarZona(String nombre, Zona zona) {
        Conexion conexion = Conexion.getInstancia();
        EntityManager em = conexion.getEntityManager();

        Usuario usr = em.find(Usuario.class, nombre);
        ((Lector) usr).setZona(zona);
        em.getTransaction().begin();
        em.merge(usr);
        em.getTransaction().commit();
    }

    
}
