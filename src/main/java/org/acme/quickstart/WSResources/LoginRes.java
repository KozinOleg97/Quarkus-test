package org.acme.quickstart.WSResources;

import org.acme.quickstart.Core.LoginHandler;
import org.acme.quickstart.Beans.Login.RequestLogin;
import org.acme.quickstart.Beans.Login.ResponseLogin;
import org.acme.quickstart.Entity.Account;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

@Path("log")
@ApplicationScoped
public class LoginRes {

    @Inject
    ResponseLogin responseLogin;
    @Inject
    LoginHandler handler;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response doLogin(RequestLogin requestLogin) throws NoSuchAlgorithmException {

        byte[] hash = handler.doHash(requestLogin.getPassword());

        Account res = Account.find("login = ?1 and password_hash = ?2",
                requestLogin.getLogin(), hash).firstResult();


        if (res != null) {
            responseLogin.setResult(true);
            responseLogin.setToken(hash);
            responseLogin.setRole(res.role.name);
            return Response.ok(responseLogin).build();
        } else {
            responseLogin.setResult(false);
            responseLogin.setToken(null);
            responseLogin.setRole(null);
            return Response.ok(responseLogin).build();
        }
    }
}
