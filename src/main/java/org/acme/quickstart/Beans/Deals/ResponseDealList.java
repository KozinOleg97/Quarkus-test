package org.acme.quickstart.Beans.Deals;

import org.acme.quickstart.Entity.Deal;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.util.List;

@ApplicationScoped
public class ResponseDealList {

     List<Deal> dealList;


     ResponseDealList() {
    }

    public List<Deal> getDealList() {
        return dealList;
    }

    public void setDealList(List<Deal> dealList) {
        this.dealList = dealList;
    }
}
