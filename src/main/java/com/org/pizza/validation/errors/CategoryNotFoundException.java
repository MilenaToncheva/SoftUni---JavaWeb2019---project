package com.org.pizza.validation.errors;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import static com.org.pizza.constant.errorMessages.category.CategoryErrorMessages.CATEGORY_NOT_FOUND_EXCEPTION;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = CATEGORY_NOT_FOUND_EXCEPTION)
public class CategoryNotFoundException extends RuntimeException {

    public CategoryNotFoundException() {
    }

    public CategoryNotFoundException(String message) {
        super(message);

    }
}