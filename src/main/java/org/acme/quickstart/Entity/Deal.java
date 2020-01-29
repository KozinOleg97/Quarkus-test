package org.acme.quickstart.Entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.enterprise.context.ApplicationScoped;
import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.List;


@Entity

public class Deal extends PanacheEntity {

    public boolean status;

    public ZonedDateTime dateTime;

//    @ManyToOne
//    @JoinColumn
//    public Auto auto;

    @ManyToOne
    @JoinColumn
    public Box box;

    @ManyToOne
    @JoinColumn
    @JsonbTransient
    public Account account;

    @OneToMany(mappedBy = "deal",cascade = CascadeType.ALL)
    @JsonbTransient
    public List<Payment> payments;

}
