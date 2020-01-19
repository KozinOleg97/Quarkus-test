package org.acme.quickstart.POJO;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ResponseClient {
    private byte[] token;
    private String nameClient;


    public ResponseClient() {
    }

    public byte[] getToken() {
        return token;
    }

    public void setToken(byte[] token) {
        this.token = token;
    }

    public String getNameClient() {
        return nameClient;
    }

    public void setNameClient(String nameClient) {
        this.nameClient = nameClient;
    }
}
