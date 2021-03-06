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


    @POST
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response register(RequestClient requestClient) {
        try {
            if (handler.checkLogin(requestClient.getLogin())) {

                handler.addNewAccount(requestClient);

                ResponseClient responseClient = new ResponseClient();
                responseClient.setResult(true);
                responseClient.setComment("");
                return Response.ok(responseClient).build();
            } else {
                return Response.status(400).build();
            }

        } catch (Exception e) {
            LOG.error("Server error (registration)", e);
            return Response.status(500).build();
        }
    }

}
