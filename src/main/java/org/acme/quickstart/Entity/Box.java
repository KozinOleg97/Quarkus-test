package org.acme.quickstart.Entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;


@Entity
public class Box extends PanacheEntity {

    @Column(nullable = false)
    public int row;

    @Column(nullable = false)
    public int col;

    @Column(nullable = false)
    public float coefficient;

    @Column(nullable = false)
    public boolean occupied = false;

    @OneToMany(mappedBy = "box", cascade = CascadeType.ALL)
    @JsonbTransient
    public List<Deal> deals;

}
