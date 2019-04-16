package com.org.pizza.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import static com.org.pizza.constant.userMessages.UserLoginMessages.WRONG_USERNAME_OR_PASSWORD;

//TODO: check
@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = WRONG_USERNAME_OR_PASSWORD)
public class WrongUsernameOrPassword extends RuntimeException {

    public WrongUsernameOrPassword() {
    }

    public WrongUsernameOrPassword(String message) {
        super(message);
    }


}
