package com.org.pizza.domain.models.service;

abstract class BaseServiceModel {
    private String id;

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
