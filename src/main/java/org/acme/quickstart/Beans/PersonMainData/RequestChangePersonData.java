package org.acme.quickstart.Beans.PersonMainData;

public class RequestChangePersonData {

    private String newName;
    private String newSurname;

    public RequestChangePersonData() {
    }

    public String getNewName() {
        return newName;
    }

    public void setNewName(String newName) {
        this.newName = newName;
    }

    public String getNewSurname() {
        return newSurname;
    }

    public void setNewSurname(String newSurname) {
        this.newSurname = newSurname;
    }
}
