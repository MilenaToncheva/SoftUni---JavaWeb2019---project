package com.org.pizza.validation.errors;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import static com.org.pizza.constant.errorMessages.ingredient.IngredientErrorMessages.INGREDIENT_NOT_FOUND_EXCEPTION;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = INGREDIENT_NOT_FOUND_EXCEPTION)
public class IngredientNotFoundException extends RuntimeException {

    public IngredientNotFoundException() {
    }

    public IngredientNotFoundException(String message) {
        super(message);

    }
}