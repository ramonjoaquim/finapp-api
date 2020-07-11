/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.finapp.api.resources;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 *
 * @author sistema
 */
@Path("heathcheck")
public class HeathCheck {
    @GET
    public String HeathCheck(){
        return "Application is up!";
    }
}
