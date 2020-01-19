package org.acme.quickstart.Entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;


import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity
public class Account extends PanacheEntity {

    public String login;
    public String password_hash;



    /*@OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    public Set<Role> role = new HashSet<Role>();*/


}
