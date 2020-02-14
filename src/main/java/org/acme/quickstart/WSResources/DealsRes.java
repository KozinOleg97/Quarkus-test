package org.acme.quickstart.WSResources;

import org.acme.quickstart.Beans.Deals.RequestDealCreate;
import org.acme.quickstart.Beans.Deals.RequestDealDisable;
import org.acme.quickstart.Entity.Account;
import org.acme.quickstart.Entity.Box;
import org.acme.quickstart.Entity.Deal;
import org.jboss.logging.Logger;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.time.ZonedDateTime;
import java.util.List;

@Path("/deal")
public class DealsRes {
    private static final Logger LOG = Logger.getLogger(DealsRes.class);


    @Path("/create")
    @POST
    @PermitAll
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response createNewDeal(@Context SecurityContext securityContext, RequestDealCreate request) {

        try {
            Account account = Account.find("login", securityContext.getUserPrincipal().getName()).firstResult();

            Box box = Box.findById(request.getBox_id());


            box.occupied = true;


            Deal deal = new Deal();
            deal.account = account;
            deal.status = true;
            deal.box = box;
            deal.dateTime = ZonedDateTime.now();
            deal.persist();


            return Response.ok(deal).build();
        } catch (Exception e) {
            LOG.debug("Server error (deal/create)", e);
            return Response.status(500).build();
        }
    }


    @Path("/disable")
    @POST
    @PermitAll
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response disableDeal(@Context SecurityContext securityContext, RequestDealDisable request) {

        try {
            Deal deal = Deal.find("id", request.getDeal_id()).firstResult();


            deal.status = false;
            deal.end_dateTime = ZonedDateTime.now();
            deal.box.occupied = false;
            //deal.box.persist();
            deal.flush();

            return Response.ok().build();
        } catch (Exception e) {
            LOG.debug("Server error (deal/disable)", e);
            return Response.status(500).build();
        }
    }

    @Path("/show/my/active")
    @PermitAll
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllMyActive(@Context SecurityContext securityContext) {
        Account account = Account.find("login", securityContext.getUserPrincipal().getName()).firstResult();

        List<Deal> dealList = Deal.list("status = ?1 and account_id = ?2", true, account.id);

        return Response.ok(dealList).build();
    }

    @Path("/show/my/disabled")
    @PermitAll
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllMyDisabled(@Context SecurityContext securityContext) {
        Account account = Account.find("login", securityContext.getUserPrincipal().getName()).firstResult();

        List<Deal> dealList = Deal.list("status = ?1 and account_id = ?2", false, account.id);

        return Response.ok(dealList).build();
    }


    @Path("/show/all")
    @RolesAllowed("admin")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll(@Context SecurityContext securityContext) {

        return Response.ok(Deal.listAll()).build();
    }


}
