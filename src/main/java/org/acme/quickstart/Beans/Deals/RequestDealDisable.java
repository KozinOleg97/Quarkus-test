package org.acme.quickstart.Beans.Deals;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class RequestDealDisable {
    private Long deal_id;

    public RequestDealDisable() {
    }

    public Long getDeal_id() {
        return deal_id;
    }

    public void setDeal_id(Long deal_id) {
        this.deal_id = deal_id;
    }
}
