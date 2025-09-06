package logica;

import java.util.List;
import java.util.ArrayList;

import persistencia.Conexion;
import persistencia.PrestamoID;
import datatypes.DtPrestamo;
import datatypes.DtBibliotecario;
import datatypes.EstadoPrestamo;

public class ManejadorPrestamos {
    private static ManejadorPrestamos instancia = null;

    private ManejadorPrestamos(){}

    public static ManejadorPrestamos getInstancia(){
        if(instancia == null){
            instancia = new ManejadorPrestamos();
        }
        return instancia;
    }
    void altaPrestamo(DtPrestamo prestamo){
        Prestamo p = new Prestamo(prestamo.getIdMaterial(), prestamo.getNombreLector(), prestamo.getIdBibliotecario(), prestamo.getFechaSolicitud(), prestamo.getFechaDevolucion(), prestamo.getEstado());
        agregarPrestamo(p);
    }

    public void agregarPrestamo(Prestamo prestamo){
        Conexion.getInstancia().getEntityManager().persist(prestamo);
    }

    public void eliminarPrestamo(Prestamo prestamo){
        Conexion.getInstancia().getEntityManager().remove(prestamo);
    }

    public void actualizarPrestamo(Prestamo prestamo){
        Conexion.getInstancia().getEntityManager().merge(prestamo);
    }

    public Prestamo buscarPrestamo(String idMaterial, String nombreLector, String idBibliotecario){
        return Conexion.getInstancia().getEntityManager().find(Prestamo.class, new PrestamoID(idMaterial, nombreLector, idBibliotecario));
    }

    public List<Prestamo> obtenerPrestamos(){
        return Conexion.getInstancia().getEntityManager().createQuery("SELECT p FROM Prestamo p", Prestamo.class).getResultList();
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


    public ArrayList<DtPrestamo> listarPrestamosBib(DtBibliotecario bibliotecario){
        List<Prestamo> prestamos = Conexion.getInstancia().getEntityManager()
            .createQuery("SELECT p FROM Prestamo p WHERE p.idBibliotecario = :idBibliotecario", Prestamo.class)
            .setParameter("idBibliotecario", bibliotecario.getNombre())
            .getResultList();
        
        ArrayList<DtPrestamo> dtPrestamos = new ArrayList<>();
        for(Prestamo p : prestamos) {
            dtPrestamos.add(new DtPrestamo(p.getIdMaterial(), p.getNombreLector(), p.getIdBibliotecario(), p.getFechaSolicitud(), p.getFechaDevolucion(), p.getEstado()));
        }
        return dtPrestamos;
    }

    public List<Prestamo> listarPrestamoPorZona(String zona) {
            List<Prestamo> prestamos = Conexion.getInstancia().getEntityManager()
            .createQuery("SELECT p FROM Prestamo p WHERE p.zona = :zona", Prestamo.class)
            .setParameter("zona", zona)
            .getResultList();
        ArrayList<DtPrestamo> dtPrestamos = new ArrayList<>();
        for(Prestamo p : prestamos) {
            dtPrestamos.add(new DtPrestamo(p.getIdMaterial(), p.getNombreLector(), p.getIdBibliotecario(), p.getFechaSolicitud(), p.getFechaDevolucion(), p.getEstado()));
        }
        return dtPrestamos;
    }
}
