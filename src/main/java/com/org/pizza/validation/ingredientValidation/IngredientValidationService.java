package com.org.pizza.validation.ingredientValidation;

import com.org.pizza.domain.entities.pizza.Ingredient;
import com.org.pizza.domain.models.service.IngredientServiceModel;

public interface IngredientValidationService {
    boolean isValid(Ingredient ingredient);

    boolean isValid(IngredientServiceModel ingredientServiceModel);
}
