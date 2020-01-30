package org.acme.quickstart;

import org.acme.quickstart.Beans.Registration.RequestClient;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

@Path("/test")
public class TestResource {



    @Path("/adminrole")
    @RolesAllowed("admin")
    //@PermitAll
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String test1(@Context SecurityContext securityContext) {

        return "Запрос от: " + securityContext.getUserPrincipal().getName();
    }


    @Path("/clientrole")
    @RolesAllowed("client")
    //@PermitAll
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String test2(@Context SecurityContext securityContext) {

        return "Запрос от: " + securityContext.getUserPrincipal().getName();
    }

    @Path("/allrole")
    @PermitAll
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String test3(@Context SecurityContext securityContext) {

        return "Запрос от: " + securityContext.getUserPrincipal().getName();
    }

}