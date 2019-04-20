package com.org.pizza.domain.models.binding;

import com.org.pizza.constant.commonMessages.CommonMessages;
import com.org.pizza.constant.pizzaMessages.PizzaCreationViolationMessages;
import com.org.pizza.domain.entities.pizza.Category;
import com.org.pizza.domain.entities.pizza.Ingredient;
import com.org.pizza.domain.models.service.CategoryServiceModel;
import com.org.pizza.domain.models.service.IngredientServiceModel;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Set;

public class PizzaAddBindingModel {
    private String name;
    private BigDecimal price;
    private MultipartFile image;
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

    public MultipartFile getImage() {
        return this.image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
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
