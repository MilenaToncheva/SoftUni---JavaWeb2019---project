package com.org.pizza.validation.errors;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import static com.org.pizza.constant.errorMessages.ingredient.IngredientErrorMessages.INGREDIENT_NOT_FOUND_EXCEPTION;
import static com.org.pizza.constant.errorMessages.pizza.PizzaErrorMessages.PIZZA_NOT_FOUND_EXCEPTION;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = PIZZA_NOT_FOUND_EXCEPTION)
public class PizzaNotFoundException extends RuntimeException {

    public PizzaNotFoundException() {
    }

    public PizzaNotFoundException(String message) {
        super(message);

    }
}