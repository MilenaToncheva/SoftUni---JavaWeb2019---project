package com.org.pizza.domain.models.binding;

import com.org.pizza.constant.commonMessages.CommonMessages;
import com.org.pizza.constant.drinkMessages.DrinkCreationViolationMessages;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Set;

public class DrinkAddBindingModel {
    private String name;
    private BigDecimal price;
    private MultipartFile image;
    private Double milliliters;
    private Set<String> ingredients;


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


    public MultipartFile getImage() {
        return this.image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }


    @NotNull
    @DecimalMin(value = "0.01", message = DrinkCreationViolationMessages.DRINK_INCORRECT_ML_VALUE)
    public Double getMilliliters() {
        return this.milliliters;
    }

    public void setMilliliters(Double milliliters) {
        this.milliliters = milliliters;
    }

    public Set<String> getIngredients() {
        return this.ingredients;
    }

    public void setIngredients(Set<String> ingredients) {
        this.ingredients = ingredients;
    }
}
