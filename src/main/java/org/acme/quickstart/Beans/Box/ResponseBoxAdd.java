package org.acme.quickstart.Beans.Box;


import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ResponseBoxAdd {
    private boolean result;
    private String comment;


     ResponseBoxAdd() {
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
