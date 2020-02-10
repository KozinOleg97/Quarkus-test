package org.acme.quickstart.WSResources;

import org.acme.quickstart.Beans.Login.ResponseLogin;
import org.jboss.logging.Logger;

import javax.annotation.security.PermitAll;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

@Path("/auth")
@ApplicationScoped
public class LoginRes {
    private static final Logger LOG = Logger.getLogger(LoginRes.class);

    @Inject
    ResponseLogin responseLogin;


    @Path("/user")
    @PermitAll
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response doAuth(@Context SecurityContext securityContext) {

        try {
            responseLogin.setResult(true);

            if (securityContext.isUserInRole("client")) {
                responseLogin.setRole("client");
            } else {
                responseLogin.setRole("admin");
            }
            return Response.ok(responseLogin).build();

        } catch (Exception e) {
            LOG.debug("Server error (auth/user)", e);
            return Response.status(500).build();
        }
    }


}
