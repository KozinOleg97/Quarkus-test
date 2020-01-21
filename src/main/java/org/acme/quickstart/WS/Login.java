package org.acme.quickstart.WS;

import org.acme.quickstart.Core.LoginHandler;
import org.acme.quickstart.POJO.RequestClient;
import org.acme.quickstart.POJO.RequestLogin;
import org.acme.quickstart.POJO.ResponseClient;
import org.acme.quickstart.POJO.ResponseLogin;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

@Path("log")
public class Login {

    //@Inject
    //ResponseLogin responseLogin;
    //LoginHandler loginHandler;

    @POST

    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseLogin Login(RequestLogin requestLogin) throws UnsupportedEncodingException, NoSuchAlgorithmException {

        LoginHandler handler = new LoginHandler();

        ResponseLogin responseLogin = handler.doLogin(requestLogin.getLogin(),
                requestLogin.getPassword());

        return responseLogin;

    }


}
