package org.acme.quickstart.Entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.enterprise.context.ApplicationScoped;
import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@Entity


public class Role extends PanacheEntity {

    @Column(unique = true, nullable = false)
    public String name;

    //TODO  lazy fetch

    @OneToMany(mappedBy = "role",cascade = CascadeType.ALL)
    @JsonbTransient
    public List<Account> accounts;

}
