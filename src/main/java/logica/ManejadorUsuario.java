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
import datatypes.DtUsuario;

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
    
    public void cambiarEstadoUsuario(String email, EstadoLector nuevoEstado) {
        Conexion conexion = Conexion.getInstancia();
        EntityManager em = conexion.getEntityManager();

        Usuario usr = em.find(Usuario.class, email);
        if (usr instanceof Lector) {
            ((Lector) usr).setEstado(nuevoEstado);
            em.getTransaction().begin();
            em.merge(usr);
            em.getTransaction().commit();
        } else {
            throw new IllegalArgumentException("Solo se pueden cambiar el estado de usuarios que sean lectores");
        }
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
    
    public DtUsuario[] obtenerLectores() {
        Conexion conexion = Conexion.getInstancia();
        EntityManager em = conexion.getEntityManager();
        
        Query query = em.createQuery("SELECT u FROM Usuario u");
        List<Usuario> usuarios = query.getResultList();
        
        ArrayList<DtUsuario> dtLectores = new ArrayList<>();
        for (Usuario usuario : usuarios) {
            if (usuario instanceof Lector) {
                dtLectores.add(usuario.getDtUsuario());
            }
        }
        return dtLectores.toArray(new DtUsuario[0]);
    }
    
    public DtUsuario[] obtenerUsuariosCompletos() {
        Conexion conexion = Conexion.getInstancia();
        EntityManager em = conexion.getEntityManager();
        
        Query query = em.createQuery("SELECT u FROM Usuario u");
        List<Usuario> usuarios = query.getResultList();
        
        DtUsuario[] dtUsuarios = new DtUsuario[usuarios.size()];
        for (int i = 0; i < usuarios.size(); i++) {
            dtUsuarios[i] = usuarios.get(i).getDtUsuario();
        }
        return dtUsuarios;
    }
}
