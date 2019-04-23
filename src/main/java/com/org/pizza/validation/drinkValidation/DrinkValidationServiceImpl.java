package com.org.pizza.validation.drinkValidation;

import com.org.pizza.domain.models.service.DrinkServiceModel;
import org.springframework.stereotype.Component;

@Component
public class DrinkValidationServiceImpl implements DrinkValidationService {
    @Override
    public boolean isValid(DrinkServiceModel drinkServiceModel) {

        return drinkServiceModel == null ||
                drinkServiceModel.getName().equals("") ||
                drinkServiceModel.getName() == null ||
                drinkServiceModel.getPrice() == null;
    }
}
