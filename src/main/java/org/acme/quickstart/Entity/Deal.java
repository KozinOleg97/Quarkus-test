package org.acme.quickstart.Entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.enterprise.context.ApplicationScoped;
import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;


@Entity

public class Deal extends PanacheEntity {

    public boolean status;


    @ManyToOne
    @JoinColumn
    public Auto auto;

    @ManyToOne
    @JoinColumn
    public Box box;

    @ManyToOne
    @JoinColumn
    @JsonbTransient
    public Account account;

}
