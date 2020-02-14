package org.acme.quickstart.Beans.Login;

public class ResponseLogin {

    private Boolean result;
    private String role;


    public ResponseLogin() {
    }

    public Boolean getResult() {
        return result;
    }

    public void setResult(Boolean result) {
        this.result = result;
    }


    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
