package org.acme.quickstart.Beans.Box;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class RequestBoxAdd {
    private int row;
    private int col;
    private Float coefficient;


    public RequestBoxAdd() {
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public Float getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(Float coefficient) {
        this.coefficient = coefficient;
    }
}
