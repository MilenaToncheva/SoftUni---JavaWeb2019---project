package com.org.pizza.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import static com.org.pizza.constant.userMessages.UserLoginMessages.WRONG_PASSWORD;
import static com.org.pizza.constant.userMessages.UserLoginMessages.WRONG_USERNAME_OR_PASSWORD;

//TODO: check
@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = WRONG_PASSWORD)
public class WrongPassword extends RuntimeException {

    public WrongPassword() {
    }

    public WrongPassword(String message) {
        super(message);
    }


}
