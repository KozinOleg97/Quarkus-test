package org.acme.quickstart.Entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@ApplicationScoped
@Entity
public class TRole extends PanacheEntity {

    @Column(unique = true, nullable = false)
    public String name;

    //TODO разобраться с lazy fetch
    @OneToMany(mappedBy = "",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    public List<TAccount> accounts;

}
