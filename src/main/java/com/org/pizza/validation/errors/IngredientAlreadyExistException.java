package com.org.pizza.validation.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import static com.org.pizza.constant.errorMessages.ingredient.IngredientErrorMessages.INGREDIENT_ALREADY_EXIST;


@ResponseStatus(code = HttpStatus.CONFLICT, reason = INGREDIENT_ALREADY_EXIST)
public class IngredientAlreadyExistException extends RuntimeException {

    public IngredientAlreadyExistException() {
    }

    public IngredientAlreadyExistException(String message) {
        super(message);
    }

}
