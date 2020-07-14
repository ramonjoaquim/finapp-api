/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.finapp.api.resources;

import br.com.finapp.models.Boleto;
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
@Path("boleto")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class BoletoResource {
    
    @PersistenceContext(unitName = "FinAppPU")
    EntityManager entityManager;
    
    @GET
    public List<Boleto> GetBoletos(){
        return entityManager
                .createQuery("SELECT b FROM Boleto b", Boleto.class)
                .getResultList();
    }
    
    @GET
    @Path("{id}")
    public Boleto getBoleto(@PathParam("id") UUID id){
        return entityManager.find(Boleto.class, id);
    }
    
    @POST
    public Response addBoleto( Boleto boleto){
        entityManager.persist(boleto);
        
        return Response
                .status(Response.Status.CREATED)
                .entity(boleto)
                .build();
    }
    
    @PUT
    @Path("{id}")
    public Boleto updateBoleto(@PathParam("id") UUID id, Boleto boleto){
        boleto.setId(id);
        entityManager.merge(id);
        
        return boleto;
    }
    
    @DELETE
    @Path("{id}")
    public void removeBoleto(@PathParam("id") UUID id){
       Boleto boleto = entityManager.find(Boleto.class, id);
       entityManager.remove(boleto);
    }
}
