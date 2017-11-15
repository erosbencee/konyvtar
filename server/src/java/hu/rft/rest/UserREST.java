/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.rft.rest;

import hu.rft.entity.User;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author Dani
 */
@Path("user")
@RequestScoped
public class UserREST {

    @Context
    private UriInfo context;
    
    

    @Inject
    private UserFacade userFacade;

    /**
     * Creates a new instance of UserREST
     */
    public UserREST() {
    }
    
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> getJson() {
        
        return userFacade.findAll();
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
    
    @GET
    @Path("/search/{name}")
    @Produces({"application/json"})
    public List<User> findByName(@PathParam("name") String name) {
        
        return userFacade.findByName(name);
    }
}
