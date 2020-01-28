package org.acme.quickstart.Beans.PersonMainData;

import org.acme.quickstart.Entity.Deal;
import org.acme.quickstart.Entity.PersonMainData;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;
@ApplicationScoped
public class ResponseAllData {


    private List<data> dataList;


    public List<data> getDataList() {
        return dataList;
    }

    public void setDataList(List<data> dataList) {
        this.dataList = dataList;
    }
}
