package com.org.pizza.domain.models.service;

import com.org.pizza.constant.pizzaMessages.PizzaCreationViolationMessages;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class CategoryServiceModel extends BaseServiceModel {

    private String categoryName;

    @NotNull
    @NotEmpty
    @Length(min = 3, max = 20, message = PizzaCreationViolationMessages.CATEGORY_INCORRECT_NAME_LENGTH)
    public String getCategoryName() {
        return this.categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
