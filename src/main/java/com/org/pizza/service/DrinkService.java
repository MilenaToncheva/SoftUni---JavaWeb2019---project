package com.org.pizza.service;

import com.org.pizza.domain.models.service.DrinkServiceModel;

import java.util.LinkedList;

public interface DrinkService {
    void addNewDrink(DrinkServiceModel drinkServiceModel);

    LinkedList<DrinkServiceModel> findAllByOrderByName();

    void deleteById(String id);
}
