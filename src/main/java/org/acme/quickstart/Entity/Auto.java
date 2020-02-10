package org.acme.quickstart.Entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity
public class Auto extends PanacheEntity {

    public String reg_number;


    @ManyToOne
    @JoinColumn
    public Account account;
}
