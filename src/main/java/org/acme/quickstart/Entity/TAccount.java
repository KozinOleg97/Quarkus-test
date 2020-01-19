package org.acme.quickstart.Entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@ApplicationScoped
@Entity
public class TAccount extends PanacheEntity {
    public String login;
    public byte[] password_hash;

    @ManyToOne
    @JoinColumn
    public TRole role;
}
