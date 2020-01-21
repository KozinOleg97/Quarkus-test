package org.acme.quickstart.POJO;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ResponseLogin {

    private Boolean result;
    private byte[] token;
    private String role;


    public Boolean getResult() {
        return result;
    }

    public void setResult(Boolean result) {
        this.result = result;
    }

    public byte[] getToken() {
        return token;
    }

    public void setToken(byte[] token) {
        this.token = token;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
