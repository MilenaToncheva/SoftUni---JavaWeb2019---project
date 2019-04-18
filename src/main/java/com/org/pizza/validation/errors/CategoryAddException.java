package com.org.pizza.validation.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import static com.org.pizza.constant.commonMessages.CommonMessages.INVALID_DATA_INPUT;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = INVALID_DATA_INPUT)
public class CategoryAddException extends RuntimeException {

    public CategoryAddException() {
    }

    public CategoryAddException(String message) {
        super(message);
    }

}
