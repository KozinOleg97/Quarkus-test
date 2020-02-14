package org.acme.quickstart.WSResources;

import org.acme.quickstart.Beans.Roles.RequestRoleChange;
import org.acme.quickstart.Core.RegistrationHandler;
import org.acme.quickstart.Entity.Account;
import org.acme.quickstart.Entity.Role;
import org.jboss.logging.Logger;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

@Path("/roles")
public class RoleRes {
    private static final Logger LOG = Logger.getLogger(RoleRes.class);

    @Inject
    RegistrationHandler handler;


    @Path("/show")
    @PermitAll
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response roleShow() {
        return Response.ok(Role.listAll()).build();
    }

    @Path("/change")
    @RolesAllowed("admin")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response roleChange( RequestRoleChange request) {
        try {
            Account account = Account.find("login", request.getLogin()).firstResult();

            account.role = handler.selectRole(request.getNewRole());

            account.flush();


            return Response.ok(true).build();

        } catch (Exception e) {
            LOG.debug("Server error (roles/change)", e);
            return Response.status(400).build();
        }
    }
}
