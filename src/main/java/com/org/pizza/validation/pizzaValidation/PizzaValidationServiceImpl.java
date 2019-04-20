package com.org.pizza.validation.pizzaValidation;

import com.org.pizza.domain.models.service.PizzaServiceModel;
import org.springframework.stereotype.Component;

@Component
public class PizzaValidationServiceImpl implements PizzaValidationService {
    @Override
    public boolean isValid(PizzaServiceModel pizzaServiceModel) {
        return pizzaServiceModel == null ||
                pizzaServiceModel.getName().equals("") ||
                pizzaServiceModel.getName() == null ||
                pizzaServiceModel.getGrams() == null ||
                pizzaServiceModel.getPrice() == null;
    }
}
