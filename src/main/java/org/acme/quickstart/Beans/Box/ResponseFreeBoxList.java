package org.acme.quickstart.Beans.Box;

import org.acme.quickstart.Entity.Box;
import org.acme.quickstart.Entity.Deal;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class ResponseFreeBoxList {

    private     List<Box> boxList;

    public List<Box> getBoxList() {
        return boxList;
    }

    public void setBoxList(List<Box> boxList) {
        this.boxList = boxList;
    }
}
