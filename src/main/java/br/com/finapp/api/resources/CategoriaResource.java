/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.finapp.api.resources;

import br.com.finapp.models.Categoria;
import java.util.List;
import java.util.UUID;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author sistema
 */

@Stateless
@Path("categoria")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CategoriaResource {
    
    @PersistenceContext(unitName = "FinAppPU")
    EntityManager entityManager;
    
    @GET
    public List<Categoria> GetCategorias(){
        return entityManager
                .createQuery("SELECT c FROM Categoria c", Categoria.class)
                .getResultList();
    }
    
    @GET
    @Path("{id}")
    public Categoria getCategoria(@PathParam("id") UUID id){
        return entityManager.find(Categoria.class, id);
    }
    
    @POST
    public Response addCategoria( Categoria categoria){
        entityManager.persist(categoria);
        
        return Response
                .status(Response.Status.CREATED)
                .entity(categoria)
                .build();
    }
    
    @PUT
    @Path("{id}")
    public Categoria updateCategoria(@PathParam("id") UUID id, Categoria categoria){
        categoria.setId(id);
        entityManager.merge(id);
        
        return categoria;
    }
    
    @DELETE
    @Path("{id}")
    public void removeCategoria(@PathParam("id") UUID id){
       Categoria categoria = entityManager.find(Categoria.class, id);
       entityManager.remove(categoria);
    }  
}
