package logica;

import java.util.List;

import persistencia.Conexion;

public class ManejadorUsuario {
    private static ManejadorUsuario instancia = null;

    private ManejadorUsuario(){}

    public static ManejadorUsuario getInstancia(){
        if(instancia == null){
            instancia = new ManejadorUsuario();
        }
        return instancia;
    }

    public void agregarUsuario(Usuario usuario){
        Conexion.getInstancia().getEntityManager().persist(usuario);
    }

    public void eliminarUsuario(Usuario usuario){
        Conexion.getInstancia().getEntityManager().remove(usuario);
    }

    public void actualizarUsuario(Usuario usuario){
        Conexion.getInstancia().getEntityManager().merge(usuario);
    }

    public Usuario buscarUsuario(String nombre){
        return Conexion.getInstancia().getEntityManager().find(Usuario.class, nombre);
    }


    public List<Usuario> obtenerUsuarios(){
        return Conexion.getInstancia().getEntityManager().createQuery("SELECT u FROM Usuario u", Usuario.class).getResultList();
    }
}
