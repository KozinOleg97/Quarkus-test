package org.acme.quickstart.Entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.List;

@ApplicationScoped
@Entity
public class Wallet extends PanacheEntity {
    public int deposit;
    public int outlay;

    public boolean is_active;


    @OneToMany(mappedBy = "wallet",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    public List<Account> accounts;
}
