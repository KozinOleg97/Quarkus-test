package org.acme.quickstart.Entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@ApplicationScoped
@Entity
public class Deal extends PanacheEntity {

    @ManyToOne
    @JoinColumn
    public Auto auto;

    @ManyToOne
    @JoinColumn
    public Box box;

    @ManyToOne
    @JoinColumn
    public Account account;

}
