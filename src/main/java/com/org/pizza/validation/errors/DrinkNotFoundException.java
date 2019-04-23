package com.org.pizza.validation.errors;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import static com.org.pizza.constant.errorMessages.drink.DrinkErrorMessages.DRINK_NOT_FOUND_EXCEPTION;
import static com.org.pizza.constant.errorMessages.ingredient.IngredientErrorMessages.INGREDIENT_NOT_FOUND_EXCEPTION;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = DRINK_NOT_FOUND_EXCEPTION)
public class DrinkNotFoundException extends RuntimeException {

    public DrinkNotFoundException() {
    }

    public DrinkNotFoundException(String message) {
        super(message);

    }
}