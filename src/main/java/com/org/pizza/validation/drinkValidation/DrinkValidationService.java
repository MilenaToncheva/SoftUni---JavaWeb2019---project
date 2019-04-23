package com.org.pizza.validation.drinkValidation;

import com.org.pizza.domain.models.service.DrinkServiceModel;

public interface DrinkValidationService {
    boolean isValid(DrinkServiceModel drinkServiceModel);
}
