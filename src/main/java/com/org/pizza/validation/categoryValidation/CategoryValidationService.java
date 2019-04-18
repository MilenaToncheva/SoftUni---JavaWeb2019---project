package com.org.pizza.validation.categoryValidation;

import com.org.pizza.domain.models.service.CategoryServiceModel;

public interface CategoryValidationService {
    boolean isValid(CategoryServiceModel categoryServiceModel);
}
