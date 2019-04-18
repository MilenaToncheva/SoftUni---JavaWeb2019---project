package com.org.pizza.service;

import com.org.pizza.domain.models.service.IngredientServiceModel;

import java.util.List;

public interface IngredientService {
    void addNewIngredient(IngredientServiceModel ingredientServiceModel);

    void editIngredient(IngredientServiceModel ingredientServiceModel, String id);

    IngredientServiceModel findIngredientById(String id);

    List<IngredientServiceModel> findAllIngredients();
}
