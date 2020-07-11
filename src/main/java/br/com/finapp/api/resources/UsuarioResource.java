/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.finapp.api.resources;

import br.com.finapp.models.Usuario;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
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
@Path("usuario")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UsuarioResource {
    
    static List<Usuario> usuarios = new ArrayList<>();
    
    private Usuario findUsuario(UUID id){
        Usuario usuario = null;
        
        for (Usuario u : usuarios){
            if(u.getId().equals(id)){
                usuario = u;
            }
        }
        
        return usuario;
    }
        
    @GET
    public List<Usuario> GetUsuarios(){
    
        return usuarios;
    }
    
    @GET
    @Path("{id}")
    public Usuario getUsuario(@PathParam("id") UUID id){
        return findUsuario(id);        
    }
    
    @POST
    public Response addUsuario( Usuario usuario){
        usuario.setId(UUID.randomUUID());
        usuarios.add(usuario);
        
        return Response
                .status(Response.Status.CREATED)
                .entity(usuario)
                .build();
    }
    
    @PUT
    @Path("{id}")
    public Usuario updateUsuario(@PathParam("id") UUID id, Usuario user){
        Usuario usuario = findUsuario(id);
        usuario.setNome(user.getNome());
        
        return usuario;
    }
    
    @DELETE
    @Path("{id}")
    public void removeUsuario(@PathParam("id") UUID id){
        Usuario usuario = findUsuario(id);
        usuarios.remove(usuario);
    }    
    
}
