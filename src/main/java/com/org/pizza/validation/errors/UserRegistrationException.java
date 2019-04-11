package com.org.pizza.validation.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import static com.org.pizza.constant.errorMessages.user.UserErrorMessages.INVALID_DATA_INPUT;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = INVALID_DATA_INPUT)
public class UserRegistrationException extends RuntimeException {

    public UserRegistrationException() {
    }

    public UserRegistrationException(String message) {
        super(message);
    }

}
