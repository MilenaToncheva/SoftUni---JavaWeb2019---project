package com.org.pizza.validation.pizzaValidation;

import com.org.pizza.domain.models.service.PizzaServiceModel;

public interface PizzaValidationService {
    boolean isValid(PizzaServiceModel pizzaServiceModel);
}
