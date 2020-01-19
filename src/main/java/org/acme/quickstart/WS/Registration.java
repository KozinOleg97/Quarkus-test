package org.acme.quickstart.WS;


import org.acme.quickstart.Core.RegistrationHandler;
import org.acme.quickstart.POJO.RequestClient;
import org.acme.quickstart.POJO.ResponseClient;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
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
    public ResponseClient Register() {
        try {

            RequestClient requestClient = new RequestClient();
            requestClient.setLogin("NewUser1");
            requestClient.setPassword("qwe");

            ResponseClient responseClient = handler.doRegistrate(
                    requestClient.getLogin(),
                    requestClient.getPassword()
            );

            return responseClient;

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }


    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseClient Register(RequestClient requestClient) {
        try {

            ResponseClient responseClient = handler.doRegistrate(
                    requestClient.getLogin(),
                    requestClient.getPassword()
            );

            return responseClient;

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

}
