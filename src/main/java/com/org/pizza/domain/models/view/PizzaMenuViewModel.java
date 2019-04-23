package com.org.pizza.domain.models.view;

import com.org.pizza.domain.models.service.CategoryServiceModel;

import java.util.Set;
import java.util.stream.Collectors;

public class PizzaMenuViewModel {

    private String imageUrl;
    private String name;
    private String price;
    private Set<String> categories;

    public String getImageUrl() {
        return this.imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return this.price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Set<String> getCategories() {
        return this.categories;
    }

    public void setCategories(Set<CategoryServiceModel> categories) {
        this.categories = categories.stream()
                .map(c -> c.getCategoryName().toUpperCase())
                .collect(Collectors.toSet());
    }
}
