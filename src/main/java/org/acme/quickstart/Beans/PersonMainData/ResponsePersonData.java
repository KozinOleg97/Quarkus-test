package org.acme.quickstart.Beans.PersonMainData;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ResponsePersonData {
    private String  name;
    private String surname;

    public ResponsePersonData() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
