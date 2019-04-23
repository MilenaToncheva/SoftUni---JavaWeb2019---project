package com.org.pizza.validation.drinkIngredientValidation;

import com.org.pizza.domain.models.service.DrinkIngredientServiceModel;
import org.springframework.stereotype.Component;

@Component
public class DrinkIngredientValidationServiceImpl implements DrinkIngredientValidationService {

    @Override
    public boolean isValid(DrinkIngredientServiceModel drinkIngredientServiceModel) {
        return drinkIngredientServiceModel == null ||
                drinkIngredientServiceModel.getIngredientName() == null ||
                drinkIngredientServiceModel.getIngredientName().equals("") ||
                drinkIngredientServiceModel.getIngredientPrice() == null;
    }
}
