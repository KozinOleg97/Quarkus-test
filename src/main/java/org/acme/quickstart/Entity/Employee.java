package org.acme.quickstart.Entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.Entity;

@ApplicationScoped
@Entity
public class Employee extends PanacheEntity {

    public String code;
}
