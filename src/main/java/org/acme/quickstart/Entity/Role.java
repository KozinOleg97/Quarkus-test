package org.acme.quickstart.Entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.*;
import java.util.List;

@ApplicationScoped
@Entity
public class Role extends PanacheEntity {

    @Column(unique = true, nullable = false)
    public String name;

    //TODO разобраться с lazy fetch
    @OneToMany(mappedBy = "role",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    public List<Account> accounts;

}
