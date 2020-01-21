package org.acme.quickstart.Entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.enterprise.context.ApplicationScoped;
import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.List;


@Entity
public class Auto extends PanacheEntity {

    public String reg_number;

    @OneToMany(mappedBy = "auto",cascade = CascadeType.ALL)
    @JsonbTransient
    public List<Deal> deals;
}
