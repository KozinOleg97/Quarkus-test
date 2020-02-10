package org.acme.quickstart.Entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.ZonedDateTime;

@Entity
public class Payment extends PanacheEntity {

    @Column(nullable = false)
    public String type;

    public String description;

    public int money;

    public ZonedDateTime date;

    @ManyToOne
    @JoinColumn
    public Deal deal;

    @ManyToOne
    @JoinColumn
    public Account account;

}
