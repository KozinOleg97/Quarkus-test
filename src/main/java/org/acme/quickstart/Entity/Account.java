package org.acme.quickstart.Entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.enterprise.context.ApplicationScoped;
import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import java.util.List;


@Entity
public class Account extends PanacheEntity {
    public String login;
    //@JsonbTransient
    public byte[] password_hash;
    public String simple_role;

    @ManyToOne
    @JoinColumn
    @JsonbTransient
    public Role role;

    @ManyToOne
    @JoinColumn
    @JsonbTransient
    public PersonMainData main_data;


    @ManyToOne
    @JoinColumn
    @JsonbTransient
    public Wallet wallet;

    @OneToMany(mappedBy = "account",cascade = CascadeType.ALL)

    public List<Deal> deals;





}
