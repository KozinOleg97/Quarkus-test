package org.acme.quickstart.Entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.enterprise.context.ApplicationScoped;
import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import java.util.List;


@Entity
public class Auto extends PanacheEntity {

    public String reg_number;


    @ManyToOne
    @JoinColumn
    public Account account;
}
