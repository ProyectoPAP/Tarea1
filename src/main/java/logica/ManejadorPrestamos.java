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
}
