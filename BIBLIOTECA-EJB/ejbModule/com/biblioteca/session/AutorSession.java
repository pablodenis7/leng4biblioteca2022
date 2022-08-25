package com.biblioteca.session;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.Query;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.biblioteca.entidad.Autor; 

@Stateless
public class AutorSession {
	@PersistenceContext (name="BibliotecaPU")
	EntityManager em; 
	
public List<Autor> consultarAutores(){
	String jpql = "SELECT a FROM Autor a ORDER BY a.codigo "; 
			
	Query q = em.createQuery(jpql); 
	List<Autor> autores = q.getResultList(); 
	
	return autores; 
}
public Map<String,Object> consultarAutoresPorNombre(String nombre){
			Map<String,Object> retorno = new HashMap<String, Object>();
			try {
				String jpql = "select a from Autor a where upper(a.nombre) like :n order by a.codigo";
				Query q = em.createQuery(jpql);
				q.setParameter("n", "%" + nombre.toUpperCase() + "%");
				List<Autor> autores = q.getResultList();
				retorno.put("success", true);
				retorno.put("result", autores);
			}catch(Exception e) {
				retorno.put("success", false);
				retorno.put("error",e.getMessage());
			}
	 
			return retorno; 
}
	
	public Autor buscarPorCodigo(Integer codigo) {
		Autor autor = em.find(Autor.class, codigo); 
		return autor;
	}
	///Inserta un autor en la base de datos
	public Autor incluir(Autor autor) {
		autor.setCodigo(null);
		em.persist(autor);//insertar
		em.refresh(autor);//consulta el dato insertado 
		return autor;
	}
	public Autor editar(Autor autor) {
		autor = em.merge(autor);
		
		return autor;
	}
	public Autor actualizar(Autor autor) {
		Autor autorActualizado = null; 
		Autor autorbuscar = buscarPorCodigo(autor.getCodigo());
		if (autorbuscar == null) {
			autorActualizado = incluir(autor);
		}else {
			autorActualizado = editar(autor);
		}
		return autorActualizado; 

	}
	public void eliminar(Integer codigo) {
		Autor autorBuscar = em.find(Autor.class, codigo);
		em.remove(autorBuscar);
		//return; 
	}
	
}
