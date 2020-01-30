package org.acme.quickstart.Beans.Auto;

import org.acme.quickstart.Entity.Auto;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
@Deprecated
public class ResponseAutoList {

    private List<Auto> autoList;

    public ResponseAutoList() {
    }

    public List<Auto> getAutoList() {
        return autoList;
    }

    public void setAutoList(List<Auto> autoList) {
        this.autoList = autoList;
    }
}
