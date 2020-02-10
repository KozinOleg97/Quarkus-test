package org.acme.quickstart.Core;


import org.acme.quickstart.Entity.Box;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class BoxHandler {

    BoxHandler() {
    }

    public boolean checkBoxExist(int row, int col) {

        Box res = Box.find("row = ?1 and col = ?2",
                row, col).firstResult();

        return res != null;
    }

    public boolean checkBoxExist(Long box_id) {

        Box res = Box.findById(box_id);

        return res != null;
    }


    public void addBox(Integer row, Integer col, float coefficient) {

        Box box = new Box();
        box.row = row;
        box.col = col;
        box.occupied = false;
        box.coefficient = coefficient;
        box.persist();

    }

}
