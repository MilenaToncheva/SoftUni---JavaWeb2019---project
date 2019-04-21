package com.org.pizza.service;

import com.org.pizza.domain.models.service.PizzaServiceModel;

import java.util.List;

public interface PizzaService {
    void addNewPizza(PizzaServiceModel pizzaServiceModel);

    List<PizzaServiceModel> findAll();
}
