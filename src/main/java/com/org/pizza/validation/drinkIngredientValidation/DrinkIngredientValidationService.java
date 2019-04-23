package com.org.pizza.validation.drinkIngredientValidation;


import com.org.pizza.domain.models.service.DrinkIngredientServiceModel;


public interface DrinkIngredientValidationService {

    boolean isValid(DrinkIngredientServiceModel drinkIngredientServiceModel);
}
