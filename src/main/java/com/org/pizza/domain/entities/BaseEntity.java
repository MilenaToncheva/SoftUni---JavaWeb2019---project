package com.org.pizza.domain.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class BaseEntity {

    private String id;

    @Id
    @GeneratedValue(generator = "uuid-string")
    @GenericGenerator(name = "uuid-string", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", nullable = false, unique = true, updatable = false, columnDefinition = "VARCHAR (36)")
    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
