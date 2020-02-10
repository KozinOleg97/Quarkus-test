package org.acme.quickstart.Entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.List;


@Entity

public class Deal extends PanacheEntity {

    public boolean status;

    public ZonedDateTime dateTime;

    public ZonedDateTime end_dateTime;


    @ManyToOne
    @JoinColumn
    public Box box;

    @ManyToOne
    @JoinColumn
    @JsonbTransient
    public Account account;

    @OneToMany(mappedBy = "deal", cascade = CascadeType.ALL)
    @JsonbTransient
    public List<Payment> payments;

}
