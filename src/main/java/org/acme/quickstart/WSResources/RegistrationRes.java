package org.acme.quickstart.WSResources;


import org.acme.quickstart.Core.RegistrationHandler;
import org.acme.quickstart.Beans.Registration.RequestClient;
import org.acme.quickstart.Beans.Registration.ResponseClient;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jboss.logging.Logger;

@Path("/registration")
public class RegistrationRes {
    private static final Logger LOG = Logger.getLogger(RegistrationRes.class);

    @Inject
    RegistrationHandler handler;
    @Inject
    ResponseClient responseClient;

    @POST
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response Register(RequestClient requestClient) {
        try {
            if (handler.checkLogin(requestClient.getLogin())) {

                handler.addNewAccount(requestClient);

                responseClient.setResult(true);
                responseClient.setComment("");
                return Response.ok(responseClient).build();
            } else {
//                responseClient.setResult(false);
//                responseClient.setComment("Такой логин уже занят");
//                return Response.ok(responseClient).build();
                return Response.status(400).build();
            }

        } catch (Exception e) {
            //e.printStackTrace();
            LOG.debug("Server error (Registration)", e);
            return Response.status(500).build();
        }
    }

}
