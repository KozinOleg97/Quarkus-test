package org.acme.quickstart.Beans.AdminPanel;

public class RequestAddAdminRole {

    private Long acc_to_change_id;

    public RequestAddAdminRole() {
    }

    public Long getAcc_to_change_id() {
        return acc_to_change_id;
    }

    public void setAcc_to_change_id(Long acc_to_change_id) {
        this.acc_to_change_id = acc_to_change_id;
    }
}
