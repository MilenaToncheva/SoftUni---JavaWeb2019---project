package com.org.pizza.service;

import com.org.pizza.domain.models.service.PizzaServiceModel;

import java.util.LinkedList;
import java.util.List;

public interface PizzaService {
    void addNewPizza(PizzaServiceModel pizzaServiceModel);

    List<PizzaServiceModel> findAll();

    List<PizzaServiceModel> findAllPizzasByCategoryName(String categoryName);

    LinkedList<PizzaServiceModel> findAllByOrderByName();

    void deleteById(String id);
}
