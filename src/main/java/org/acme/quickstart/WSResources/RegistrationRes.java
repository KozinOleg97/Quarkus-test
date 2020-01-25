package org.acme.quickstart.WSResources;


import org.acme.quickstart.Core.RegistrationHandler;
import org.acme.quickstart.Beans.Registration.RequestClient;
import org.acme.quickstart.Beans.Registration.ResponseClient;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

@Path("/reg")
public class RegistrationRes {

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

                responseClient.setNameClient(requestClient.getLogin());
                responseClient.setToken(handler.doHash(requestClient.getPassword()));
                return Response.ok(responseClient).build();
            } else {
                responseClient.setNameClient(requestClient.getLogin());
                responseClient.setToken(null);
                return Response.ok(responseClient).build();
            }

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

}
