package org.acme.quickstart.Beans.Registration;

public class ResponseClient {
    private boolean result;
    private String comment;


    public ResponseClient() {
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
