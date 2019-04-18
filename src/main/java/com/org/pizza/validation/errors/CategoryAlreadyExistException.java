package com.org.pizza.validation.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import static com.org.pizza.constant.errorMessages.category.CategoryErrorMessages.CATEGORY_ALREADY_EXIST;


@ResponseStatus(code = HttpStatus.CONFLICT, reason = CATEGORY_ALREADY_EXIST)
public class CategoryAlreadyExistException extends RuntimeException {

    public CategoryAlreadyExistException() {
    }

    public CategoryAlreadyExistException(String message) {
        super(message);
    }

}
