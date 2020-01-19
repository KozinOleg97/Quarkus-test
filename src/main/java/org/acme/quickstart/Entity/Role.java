package org.acme.quickstart.Entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Role extends PanacheEntity {

    public String description;

    @OneToMany(mappedBy = "role")
    public Set<TAccount> accounts = new HashSet<TAccount>();


    /*@ManyToOne
    @JoinColumn(name = "role", nullable = false, unique = true)
    public Account account;*/

    /*public RoleType type;
    public enum RoleType{
        admin, base;
    }*/


}
