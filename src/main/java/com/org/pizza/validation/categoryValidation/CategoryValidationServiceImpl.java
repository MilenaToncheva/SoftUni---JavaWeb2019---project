package com.org.pizza.validation.categoryValidation;

import com.org.pizza.domain.models.service.CategoryServiceModel;
import org.springframework.stereotype.Component;

@Component
public class CategoryValidationServiceImpl implements CategoryValidationService {
    @Override
    public boolean isValid(CategoryServiceModel categoryServiceModel) {
        return categoryServiceModel.getCategoryName() == null
                || "".equals(categoryServiceModel.getCategoryName());
    }
}
