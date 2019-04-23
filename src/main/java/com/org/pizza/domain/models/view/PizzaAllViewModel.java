package com.org.pizza.domain.models.view;

import com.org.pizza.domain.models.service.CategoryServiceModel;
import com.org.pizza.domain.models.service.IngredientServiceModel;

import java.math.BigDecimal;
import java.util.Set;
import java.util.stream.Collectors;

public class PizzaAllViewModel {
    private String id;
    private String name;
    private BigDecimal price;
    private Double grams;
    private Set<String> ingredients;
    private Set<String> categories;

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

    public Double getGrams() {
        return this.grams;
    }

    public void setGrams(Double grams) {
        this.grams = grams;
    }

    public Set<String> getIngredients() {
        return this.ingredients;
    }

    public void setIngredients(Set<IngredientServiceModel> ingredients) {
        this.ingredients = ingredients.stream()
                .map(i -> i.getIngredientName())
                .collect(Collectors.toSet());
    }

    public Set<String> getCategories() {
        return this.categories;
    }

    public void setCategories(Set<CategoryServiceModel> categories) {
        this.categories = categories.stream()
                .map(c -> c.getCategoryName())
                .collect(Collectors.toSet());
    }
}
