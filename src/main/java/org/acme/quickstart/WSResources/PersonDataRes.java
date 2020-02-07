package org.acme.quickstart.WSResources;

import org.acme.quickstart.Beans.PersonMainData.*;
import org.acme.quickstart.Entity.*;

import javax.annotation.security.PermitAll;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import org.jboss.logging.Logger;

@Path("/persondata")
public class PersonDataRes {
    private static final Logger LOG = Logger.getLogger(PersonDataRes.class);

    @Inject
    ResponsePersonData responsePersonData;


    @Path("/show")
    @PermitAll
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll(@Context SecurityContext securityContext) {

        Account account = Account.find("login", securityContext.getUserPrincipal().getName()).firstResult();

        responsePersonData.setName(account.main_data.Name);
        responsePersonData.setSurname(account.main_data.Surname);

        return Response.ok(responsePersonData).build();
    }


    @Path("/change")
    @PermitAll
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response createNewDeal(@Context SecurityContext securityContext, RequestChangePersonData request) {
        try {
            Account account = Account.find("login", securityContext.getUserPrincipal().getName()).firstResult();

            account.main_data.Name = request.getNewName();
            account.main_data.Surname = request.getNewSurname();

            account.persist();
            return Response.ok(true).build();

        } catch (Exception e) {
            LOG.error("Server error (persondata/change)", e);
            return Response.status(500).build();
        }
    }
}
