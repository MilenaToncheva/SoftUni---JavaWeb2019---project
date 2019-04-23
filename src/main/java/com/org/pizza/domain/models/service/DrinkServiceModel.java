package com.org.pizza.domain.models.service;

import com.org.pizza.constant.commonMessages.CommonMessages;
import com.org.pizza.constant.drinkMessages.DrinkCreationViolationMessages;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Set;

public class DrinkServiceModel extends BaseServiceModel {
    private String name;
    private BigDecimal price;
    private String imageUrl;
    private Double milliliters;
    private Set<DrinkIngredientServiceModel> ingredients;


    @NotEmpty
    @NotNull
    @Length(min = 3, max = 20, message = DrinkCreationViolationMessages.DRINK_INCORRECT_NAME_LENGTH)
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NotNull
    @DecimalMin(value = "0.01", message = CommonMessages.MIN_PRICE)
    public BigDecimal getPrice() {
        return this.price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getImageUrl() {
        return this.imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }


    @NotNull
    @DecimalMin(value = "0.01", message = DrinkCreationViolationMessages.DRINK_INCORRECT_ML_VALUE)
    public Double getMilliliters() {
        return this.milliliters;
    }

    public void setMilliliters(Double milliliters) {
        this.milliliters = milliliters;
    }

    public Set<DrinkIngredientServiceModel> getIngredients() {
        return this.ingredients;
    }

    public void setIngredients(Set<DrinkIngredientServiceModel> ingredients) {
        this.ingredients = ingredients;
    }
}
