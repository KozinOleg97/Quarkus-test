package org.acme.quickstart.Entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.*;
import java.util.List;

@ApplicationScoped
@Entity
public class Box extends PanacheEntity {

    @Column(nullable = false)
    public int row;

    @Column(nullable = false)
    public int col;

    @OneToMany(mappedBy = "box",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    public List<Deal> deals;

}
