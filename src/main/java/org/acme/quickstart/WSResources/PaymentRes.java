package org.acme.quickstart.WSResources;

import org.acme.quickstart.Beans.Payment.RequestPaymentIncome;
import org.acme.quickstart.Entity.Account;
import org.acme.quickstart.Entity.Payment;
import org.jboss.logging.Logger;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.util.List;

@Path("payment")
public class PaymentRes {
    private static final Logger LOG = Logger.getLogger(Payment.class);


    @Path("/show/all")
    @RolesAllowed("admin")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response showPaymentList() {

        return Response.ok(Payment.listAll()).build();

    }


    @Path("/show/my")
    @PermitAll
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response showMyPaymentList(@Context SecurityContext securityContext) {
        Account account = Account.find("login", securityContext.getUserPrincipal().getName()).firstResult();

        List<Payment> resList = Payment.list("account_id", account.id);

        return Response.ok(resList).build();
    }

    @Path("/income")
    @PermitAll
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response addNewIncomeDoc(@Context SecurityContext securityContext, RequestPaymentIncome request) {
        try {
            Account account = Account.find("login", securityContext.getUserPrincipal().getName()).firstResult();

            Payment payment = new Payment();

            payment.account = account;
            payment.date = request.getDateTime();
            payment.description = request.getDescription();
            payment.money = request.getMoney();
            payment.type = "income";

            payment.persist();

            return Response.ok().build();

        } catch (Exception e) {
            LOG.debug("Server error (payment/income)", e);
            return Response.status(500).build();
        }
    }


}
