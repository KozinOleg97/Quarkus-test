package org.acme.quickstart.Exceptions;

public class NoSuchRole extends Exception {

    private String role;

    public String getRole() {
        return role;
    }

    public NoSuchRole(String message, String role) {
        super(message);
        this.role = role;
    }
}
