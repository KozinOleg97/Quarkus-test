package org.acme.quickstart.Entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity


public class Role extends PanacheEntity {

    @Column(unique = true, nullable = false)
    public String name;


    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL)
    @JsonbTransient
    public List<Account> accounts;

}
