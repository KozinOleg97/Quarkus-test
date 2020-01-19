package org.acme.quickstart.WS;


import org.acme.quickstart.Core.RegistrationHandler;
import org.acme.quickstart.POJO.RequestClient;
import org.acme.quickstart.POJO.ResponseClient;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
@Path("/reg")
public class Registration {

    @Inject
    RegistrationHandler handler;

    /**
     * Test method
     * @return
     */

    @GET

    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public ResponseClient Register() {
        try {

            RequestClient requestClient = new RequestClient();
            requestClient.setLogin("NewUser2");
            requestClient.setPassword("qwe");


            ResponseClient responseClient = handler.doRegister(
                    requestClient.getLogin(),
                    requestClient.getPassword()
            );

            return responseClient;

        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseClient Register(RequestClient requestClient) {
        try {

            ResponseClient responseClient = handler.doRegister(
                    requestClient.getLogin(),
                    requestClient.getPassword()
            );

            return responseClient;

        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

}
