package org.acme.quickstart.Entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Entity;

@Entity
public class Account extends PanacheEntity {
    public String login;
    public String password_hash;
    public Role role;
}
