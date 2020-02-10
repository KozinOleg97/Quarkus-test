package org.acme.quickstart.Entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import java.util.List;


@Entity
public class Account extends PanacheEntity {
    public String login;
    //@JsonbTransient


    @JsonbTransient
    public String password;


    @ManyToOne
    @JoinColumn

    public Role role;

    @ManyToOne
    @JoinColumn
    @JsonbTransient
    public PersonMainData main_data;


    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    @JsonbTransient
    public List<Deal> deals;


    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    @JsonbTransient
    public List<Payment> payments;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    @JsonbTransient
    public List<Auto> autos;


}
