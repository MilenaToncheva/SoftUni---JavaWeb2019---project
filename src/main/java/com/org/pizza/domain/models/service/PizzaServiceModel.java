package com.org.pizza.domain.models.service;

import com.org.pizza.constant.commonMessages.CommonMessages;
import com.org.pizza.constant.pizzaMessages.PizzaCreationViolationMessages;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Set;

public class PizzaServiceModel extends BaseServiceModel {

    private String name;
    private BigDecimal price;
    private String imageUrl;
    private Double grams;
    private Set<IngredientServiceModel> ingredients;
    private Set<CategoryServiceModel> categories;

    @NotEmpty
    @NotNull
    @Length(min = 3, max = 20, message = PizzaCreationViolationMessages.PIZZA_INCORRECT_NAME_LENGTH)
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NotNull
    @NotEmpty
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

    @NotEmpty
    @NotNull
    @DecimalMin(value = "0.01", message = PizzaCreationViolationMessages.PIZZA_INCORRECT_GRAM_VALUE)
    public Double getGrams() {
        return this.grams;
    }

    public void setGrams(Double grams) {
        this.grams = grams;
    }

    public Set<IngredientServiceModel> getIngredients() {
        return this.ingredients;
    }

    public void setIngredients(Set<IngredientServiceModel> ingredients) {
        this.ingredients = ingredients;
    }

    public Set<CategoryServiceModel> getCategories() {
        return this.categories;
    }

    public void setCategories(Set<CategoryServiceModel> categories) {
        this.categories = categories;
    }
}
