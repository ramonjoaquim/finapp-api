/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.finapp.api.resources;

import br.com.finapp.models.Lancamento;
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
@Path("lancamento")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class LancamentoResource {
    
    @PersistenceContext(unitName = "FinAppPU")
    EntityManager entityManager;
    
    @GET
    public List<Lancamento> GetLancamentos(){
        return entityManager
                .createQuery("SELECT l FROM Lancamento l", Lancamento.class)
                .getResultList();
    }
    
    @GET
    @Path("{id}")
    public Lancamento getLancamento(@PathParam("id") UUID id){
        return entityManager.find(Lancamento.class, id);
    }
    
    @POST
    public Response addLancamento( Lancamento lancamento){
        entityManager.persist(lancamento);
        
        return Response
                .status(Response.Status.CREATED)
                .entity(lancamento)
                .build();
    }
    
    @PUT
    @Path("{id}")
    public Lancamento updateLancamento(@PathParam("id") UUID id, Lancamento lancamento){
        lancamento.setId(id);
        entityManager.merge(id);
        
        return lancamento;
    }
    
    @DELETE
    @Path("{id}")
    public void removeLancamento(@PathParam("id") UUID id){
       Lancamento lancamento = entityManager.find(Lancamento.class, id);
       entityManager.remove(lancamento);
    }  
    
}
