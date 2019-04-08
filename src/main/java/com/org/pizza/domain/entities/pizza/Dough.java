package com.org.pizza.domain.entities.pizza;

public enum Dough {
    NORMAL("Normal"), ITALIAN("Italian"), CRUST("Thin Crust");

    private String type;

    Dough(String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }
}
