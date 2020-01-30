package org.acme.quickstart.WSResources;

import org.acme.quickstart.Core.LoginHandler;
import org.acme.quickstart.Beans.Login.ResponseLogin;

import javax.annotation.security.PermitAll;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

@Path("auth")
@ApplicationScoped
public class LoginRes {

    @Inject
    ResponseLogin responseLogin;


    @Path("/user")
    @PermitAll
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response doAuth(@Context SecurityContext securityContext) {

        responseLogin.setResult(true);

        if (securityContext.isUserInRole("client")) {
            responseLogin.setRole("client");
        } else {
            responseLogin.setRole("admin");
        }

        return Response.ok(responseLogin).build();
    }


}
