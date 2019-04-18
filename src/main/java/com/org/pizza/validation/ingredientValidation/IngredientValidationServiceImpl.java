package com.org.pizza.validation.ingredientValidation;

import com.org.pizza.domain.entities.pizza.Ingredient;
import com.org.pizza.domain.models.service.IngredientServiceModel;
import org.springframework.stereotype.Component;

@Component
public class IngredientValidationServiceImpl implements IngredientValidationService {
    @Override
    public boolean isValid(Ingredient ingredient) {
        return ingredient == null ||
                ingredient.getIngredientName() == null ||
                ingredient.getIngredientName().equals("") ||
                ingredient.getIngredientPrice() == null;
    }

    @Override
    public boolean isValid(IngredientServiceModel ingredientServiceModel) {
        return ingredientServiceModel == null ||
                ingredientServiceModel.getIngredientName() == null ||
                ingredientServiceModel.getIngredientName().equals("") ||
                ingredientServiceModel.getIngredientPrice() == null;
    }
}
