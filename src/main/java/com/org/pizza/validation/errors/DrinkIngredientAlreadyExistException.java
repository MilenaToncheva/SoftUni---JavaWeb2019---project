package com.org.pizza.validation.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import static com.org.pizza.constant.errorMessages.ingredient.IngredientErrorMessages.INGREDIENT_ALREADY_EXIST;


@ResponseStatus(code = HttpStatus.CONFLICT, reason = INGREDIENT_ALREADY_EXIST)
public class DrinkIngredientAlreadyExistException extends RuntimeException {

    public DrinkIngredientAlreadyExistException() {
    }

    public DrinkIngredientAlreadyExistException(String message) {
        super(message);
    }

}
