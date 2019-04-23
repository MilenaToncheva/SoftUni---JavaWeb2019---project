package com.org.pizza.domain.models.view;

import com.org.pizza.domain.models.service.DrinkIngredientServiceModel;

import java.math.BigDecimal;
import java.util.Set;
import java.util.stream.Collectors;

public class DrinkAllViewModel {

    private String id;
    private String name;
    private BigDecimal price;
    private String imageUrl;
    private Double milliliters;
    private Set<String> ingredients;

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }


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

    public Double getMilliliters() {
        return this.milliliters;
    }

    public void setMilliters(Double milliliters) {
        this.milliliters = milliliters;
    }


    public Set<String> getIngredients() {
        return this.ingredients;
    }

    public void setIngredients(Set<DrinkIngredientServiceModel> ingredients) {
        this.ingredients = ingredients.stream()
                .map(i -> i.getIngredientName())
                .collect(Collectors.toSet());
    }
}
