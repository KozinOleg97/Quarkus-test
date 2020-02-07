package org.acme.quickstart.WSResources;

import org.acme.quickstart.Beans.AdminPanel.RequestAddAdminRole;
import org.acme.quickstart.Core.RegistrationHandler;
import org.acme.quickstart.Entity.Account;
import org.jboss.logging.Logger;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

@Path("/admin")
public class AdminPanelRes {
    private static final Logger LOG = Logger.getLogger(AdminPanelRes.class);


    @Inject
    RegistrationHandler registrationHandler;

    @Path("/accounts")
    @RolesAllowed("admin")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response showAccountList(@Context SecurityContext securityContext) {

        return Response.ok(Account.listAll()).build();
    }

    @Path("/addadmin")
    @RolesAllowed("admin")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response setAdminRole(@Context SecurityContext securityContext, RequestAddAdminRole request) {
        try {

            Account account = Account.find("id", request.getAcc_to_change_id()).firstResult();

            account.role = registrationHandler.selectRole("admin");
            account.persist();

            return Response.ok().build();
        } catch (Exception e) {
            LOG.debug("Server error (panel/addadmin)", e);
            return Response.status(500).build();
        }

    }

    @Path("/removeaddmin")
    @RolesAllowed("admin")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response removeAdminRole(RequestAddAdminRole request) {
        try {

            Account account = Account.find("id", request.getAcc_to_change_id()).firstResult();

            account.role = registrationHandler.selectRole("client");
            account.persist();


            return Response.ok().build();
        } catch (Exception e) {
            LOG.debug("Server error (panel/removeaddmin)", e);
            return Response.status(500).build();
        }

    }
}
