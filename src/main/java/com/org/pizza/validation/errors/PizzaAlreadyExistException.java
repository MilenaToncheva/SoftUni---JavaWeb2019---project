package com.org.pizza.validation.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import static com.org.pizza.constant.errorMessages.pizza.PizzaErrorMessages.PIZZA_ALREADY_EXIST;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = PIZZA_ALREADY_EXIST)
public class PizzaAlreadyExistException extends RuntimeException {

    public PizzaAlreadyExistException() {
    }

    public PizzaAlreadyExistException(String message) {
        super(message);
    }
}




