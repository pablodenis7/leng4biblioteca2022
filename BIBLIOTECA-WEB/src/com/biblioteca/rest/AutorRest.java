package com.biblioteca.rest;

import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.websocket.server.PathParam;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;


import com.biblioteca.entidad.Autor;
import com.biblioteca.session.AutorSession;

@Path("/autor")

public class AutorRest {

	@EJB 
	AutorSession as; 
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/consultar")
	public List<Autor> consultar() {
		return as.consultarAutores(); 

	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/consultar-por-nombre")
	//Parametros: QueryParam, PathParam , BodyParam 
	public Map<String,Object> consultarPorNombre( @QueryParam("nombre") String nombre) {
		return as.consultarAutoresPorNombre(nombre);

	}
	//buscarPorCodigo 
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/buscar-Por-Codigo")
	//Parametros: QueryParam, PathParam , BodyParam 
	public Autor buscarporCodigo(@QueryParam("codigo") Integer codigo) {
		return as.buscarPorCodigo(codigo);
	}
	//editar
	@POST 
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/editar")
	public Autor editar( Autor autor){
		return as.editar(autor);
	}
	
	

	
	//actualizar
		@POST 
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/actualizar")
	public Autor actualizar( Autor autor){
		return as.actualizar(autor);
	}
	
	
	
	
	

	
	@POST 
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/incluir")
	public Autor incluir( Autor autor){
		return as.incluir(autor);
	}
	
	@DELETE
	@Path("/eliminar/{id}")
	public void eliminar(@PathParam("id") Integer codigo){
			as.eliminar(codigo);
	}
}
