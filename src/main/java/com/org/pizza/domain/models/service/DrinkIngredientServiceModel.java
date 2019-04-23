package com.org.pizza.domain.models.service;

import com.org.pizza.constant.commonMessages.CommonMessages;
import com.org.pizza.constant.drinkMessages.DrinkCreationViolationMessages;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class DrinkIngredientServiceModel extends BaseServiceModel {
    private String ingredientName;
    private BigDecimal ingredientPrice;


    @NotNull
    @NotEmpty
    @Length(min = 3, max = 20, message = DrinkCreationViolationMessages.INGREDIENT_INCORRECT_NAME_LENGTH)
    public String getIngredientName() {
        return this.ingredientName;
    }

    public void setIngredientName(String ingredientName) {
        this.ingredientName = ingredientName;
    }

    @NotNull
    @DecimalMin(value = "0.01", message = CommonMessages.MIN_PRICE)
    public BigDecimal getIngredientPrice() {
        return this.ingredientPrice;
    }

    public void setIngredientPrice(BigDecimal ingredientPrice) {
        this.ingredientPrice = ingredientPrice;
    }
}
