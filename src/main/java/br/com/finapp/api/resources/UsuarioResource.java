/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.finapp.api.resources;

import br.com.finapp.models.Usuario;
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
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author sistema
 */

@Stateless
@Path("usuario")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UsuarioResource {
    
    @PersistenceContext(unitName = "FinAppPU")
    EntityManager entityManager;
               
    @GET
    public List<Usuario> GetUsuarios(@QueryParam("usuario") String usuario, @QueryParam("senha") String senha ){
        if (usuario == null || usuario.isBlank() || senha == null || senha.isBlank()){
            return entityManager
                .createNamedQuery("Usuario.FindAll", Usuario.class)
                .getResultList();
        }else{
            return entityManager
                .createNamedQuery("Usuario.FindByUserAndPassword", Usuario.class)
                .setParameter("usuario", usuario)
                .setParameter("senha", senha)
                .getResultList();
        }       
        
    }
    
    @GET
    @Path("{id}")
    public Usuario getUsuario(@PathParam("id") UUID id){
        return entityManager.find(Usuario.class, id);
    }
    
    @POST
    public Response addUsuario( Usuario usuario){
        entityManager.persist(usuario);
        
        return Response
                .status(Response.Status.CREATED)
                .entity(usuario)
                .build();
    }
    
    @PUT
    @Path("{id}")
    public Usuario updateUsuario(@PathParam("id") UUID id, Usuario user){
        user.setId(id);
        entityManager.merge(id);
        
        return user;
    }
    
    @DELETE
    @Path("{id}")
    public void removeUsuario(@PathParam("id") UUID id){
       Usuario usuario = entityManager.find(Usuario.class, id);
       entityManager.remove(usuario);
    }    
    
}

