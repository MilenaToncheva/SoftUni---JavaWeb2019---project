package com.org.pizza.validation.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import static com.org.pizza.constant.errorMessages.drink.DrinkErrorMessages.DRINK_ALREADY_EXIST;
import static com.org.pizza.constant.errorMessages.pizza.PizzaErrorMessages.PIZZA_ALREADY_EXIST;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = DRINK_ALREADY_EXIST)
public class DrinkAlreadyExistException extends RuntimeException {

    public DrinkAlreadyExistException() {
    }

    public DrinkAlreadyExistException(String message) {
        super(message);
    }
}




