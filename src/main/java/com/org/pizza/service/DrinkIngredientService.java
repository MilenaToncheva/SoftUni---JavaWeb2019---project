package com.org.pizza.service;

import com.org.pizza.domain.models.service.DrinkIngredientServiceModel;

import java.util.Set;

public interface DrinkIngredientService {
    void addNewIngredient(DrinkIngredientServiceModel ingredientServiceModel);

    void editIngredient(DrinkIngredientServiceModel drinkIngredientServiceModel);

    DrinkIngredientServiceModel findIngredientById(String id);

    Set<DrinkIngredientServiceModel> findAllDrinkIngredients();

    void deleteById(String id);
}
