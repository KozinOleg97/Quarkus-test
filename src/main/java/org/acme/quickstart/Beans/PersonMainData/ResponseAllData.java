package org.acme.quickstart.Beans.PersonMainData;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;
@ApplicationScoped
@Deprecated
public class ResponseAllData {


    private List<ResponsePersonData> responsePersonDataList;


    public List<ResponsePersonData> getResponsePersonDataList() {
        return responsePersonDataList;
    }

    public void setResponsePersonDataList(List<ResponsePersonData> responsePersonDataList) {
        this.responsePersonDataList = responsePersonDataList;
    }
}
