package org.acme.quickstart.WSResources;

import org.acme.quickstart.Beans.Auto.RequestAutoAdd;
import org.acme.quickstart.Beans.Auto.RequestAutoAddTo;
import org.acme.quickstart.Core.AutoHandler;
import org.acme.quickstart.Entity.Account;
import org.acme.quickstart.Entity.Auto;
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
import java.util.List;


@Path("/auto")
public class AutoRes {
    private static final Logger LOG = Logger.getLogger(AutoRes.class);

    @Inject
    AutoHandler handler;


    @Path("/add/me")
    @PermitAll
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response addAuto(@Context SecurityContext securityContext, RequestAutoAdd request) {
        try {
            if (handler.checkAutoExist(request.getRegNumber())) {
                return Response.status(400).build();
            } else {

                handler.addAuto(request.getRegNumber(), securityContext.getUserPrincipal().getName());
                return Response.ok(true).build();
            }
        } catch (Exception e) {
            LOG.debug("Server error (auto/add)", e);
            return Response.status(500).build();
        }
    }

    @Path("/add/to")
    @RolesAllowed("admin")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response addAutoTo(@Context SecurityContext securityContext, RequestAutoAddTo request) {
        try {
            if (handler.checkAutoExist(request.getRegNumber())) {
                return Response.status(400).build();
            } else {

                handler.addAuto(request.getRegNumber(), request.getLogin());
                return Response.ok(true).build();
            }
        } catch (Exception e) {
            LOG.error("Server error (auto/add)", e);
            return Response.status(500).build();
        }
    }


    @Path("/show/all")
    @RolesAllowed("admin")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response showAutoAll() {

        return Response.ok(Auto.listAll()).build();
    }

    @Path("/show/my")
    @PermitAll
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response showAutoFree(@Context SecurityContext securityContext) {
        try {
            Account account = Account.find("login", securityContext.getUserPrincipal().getName()).firstResult();

            List<Auto> resList = Auto.list("account_id", account.id);

            return Response.ok(resList).build();
        } catch (Exception e) {

            LOG.error("Server error (auto/show/free)", e);
            return Response.status(500).build();
        }
    }
}
