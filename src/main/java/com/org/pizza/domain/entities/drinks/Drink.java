package com.org.pizza.domain.entities.drinks;

import com.org.pizza.constant.commonMessages.CommonMessages;
import com.org.pizza.constant.drinkMessages.DrinkCreationViolationMessages;
import com.org.pizza.domain.entities.BaseEntity;
import com.org.pizza.domain.entities.pizza.Ingredient;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table(name = "drinks")
public class Drink extends BaseEntity {
    private String name;
    private BigDecimal price;
    private String imageUrl;
    private Double milliliters;
    private Set<DrinkIngredient> ingredients;


    @NotEmpty
    @Column(name = "name", nullable = false, unique = true, columnDefinition = "VARCHAR (20)")
    @Length(min = 3, max = 20, message = DrinkCreationViolationMessages.DRINK_INCORRECT_NAME_LENGTH)
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "price", nullable = false)
    @DecimalMin(value = "0.01", message = CommonMessages.MIN_PRICE)
    public BigDecimal getPrice() {
        return this.price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Column(name = "image")
    public String getImageUrl() {
        return this.imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Column(name = "milliliters", nullable = false)
    @DecimalMin(value = "0.01", message = DrinkCreationViolationMessages.DRINK_INCORRECT_ML_VALUE)
    public Double getMilliliters() {
        return this.milliliters;
    }

    public void setMilliliters(Double milliliters) {
        this.milliliters = milliliters;
    }

    @ManyToMany(targetEntity = DrinkIngredient.class)
    @JoinTable(name = "drinks_ingredients",
            joinColumns = @JoinColumn(name = "drink_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "ingredient_id", referencedColumnName = "id"))
    public Set<DrinkIngredient> getIngredients() {
        return this.ingredients;
    }

    public void setIngredients(Set<DrinkIngredient> ingredients) {
        this.ingredients = ingredients;
    }

}
