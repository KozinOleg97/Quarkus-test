package org.acme.quickstart.Entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class PersonMainData extends PanacheEntity {

    public String Name;
    public String Surname;

    @OneToMany(mappedBy = "main_data",cascade = CascadeType.ALL)
    public List<Account> accounts;
}
