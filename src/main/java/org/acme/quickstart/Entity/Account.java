package org.acme.quickstart.Entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.*;
import java.util.List;

@ApplicationScoped
@Entity
public class Account extends PanacheEntity {
    public String login;
    public byte[] password_hash;
    public String simple_role;

    @ManyToOne
    @JoinColumn
    public Role role;

    @ManyToOne
    @JoinColumn
    public PersonMainData main_data;


    @ManyToOne
    @JoinColumn
    public Wallet wallet;

    @OneToMany(mappedBy = "account",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    public List<Deal> deals;





}
