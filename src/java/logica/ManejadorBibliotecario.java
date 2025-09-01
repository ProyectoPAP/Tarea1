package logica;

import java.util.ArrayList;
import java.util.List;

import persistencia.Conexion;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public class ManejadorBibliotecario{

    private static ManejadorBibliotecario instancia = null;
	
	private ManejadorBibliotecario(){}
	
	public static ManejadorBibliotecario getInstancia() {
		if (instancia == null)
			instancia = new ManejadorBibliotecario();
		return instancia;
	}

	public void agregarBibliotecario(Bibliotecario Bibliotecario) {
		Conexion conexion = Conexion.getInstancia();
		EntityManager em = conexion.getEntityManager();
		em.getTransaction().begin();
		
		em.persist(Bibliotecario);
		
		em.getTransaction().commit();
	}
	
	public Bibliotecario buscarBibliotecario(int id) {
		Conexion conexion = Conexion.getInstancia();
		EntityManager em = conexion.getEntityManager();
		
		Bibliotecario Bibliotecario = em.find(Bibliotecario.class, id);
		return Bibliotecario;
	}
	
	public ArrayList<Integer> obtenerBibliotecarios(){
		Conexion conexion = Conexion.getInstancia();
		EntityManager em = conexion.getEntityManager();
	
		Query query = em.createQuery("select c from Bibliotecario c");
		
		List<Bibliotecario> listBibliotecario = (List<Bibliotecario>) query.getResultList();
		
		ArrayList<Integer> aRetornar = new ArrayList<>();
		for(Bibliotecario c: listBibliotecario) {
			aRetornar.add(new Integer(c.getId()));
		}
		return aRetornar;
	}
	
	
}