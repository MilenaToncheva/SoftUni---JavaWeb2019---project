package com.org.pizza.domain.models.view;

import java.math.BigDecimal;

public class DrinkIngredientViewModel {
    private String id;
    private String ingredientName;
    private BigDecimal ingredientPrice;

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIngredientName() {
        return this.ingredientName;
    }

    public void setIngredientName(String ingredientName) {
        this.ingredientName = ingredientName;
    }

    public BigDecimal getIngredientPrice() {
        return this.ingredientPrice;
    }

    public void setIngredientPrice(BigDecimal ingredientPrice) {
        this.ingredientPrice = ingredientPrice;
    }
}
