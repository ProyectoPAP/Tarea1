package logica;

import java.util.ArrayList;
import java.util.List;

import persistencia.Conexion;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public class ManejadorLector{

    private static ManejadorLector instancia = null;
	
	private ManejadorLector(){}
	
	public static ManejadorLector getInstancia() {
		if (instancia == null)
			instancia = new ManejadorLector();
		return instancia;
	}

	public void agregarLector(Lector Lector) {
		Conexion conexion = Conexion.getInstancia();
		EntityManager em = conexion.getEntityManager();
		em.getTransaction().begin();
		
		em.persist(Lector);
		
		em.getTransaction().commit();
	}
	
	public Lector buscarLector(int id) {
		Conexion conexion = Conexion.getInstancia();
		EntityManager em = conexion.getEntityManager();
		
		Lector Lector = em.find(Lector.class, id);
		return Lector;
	}
	
	public ArrayList<Integer> obtenerLectores(){
		Conexion conexion = Conexion.getInstancia();
		EntityManager em = conexion.getEntityManager();
	
		Query query = em.createQuery("select c from Lector c");
		
		List<Lector> listLector = (List<Lector>) query.getResultList();
		
		ArrayList<Integer> aRetornar = new ArrayList<>();
		for(Lector c: listLector) {
			aRetornar.add(new Integer(c.getId()));
		}
		return aRetornar;
	}
	
	
}