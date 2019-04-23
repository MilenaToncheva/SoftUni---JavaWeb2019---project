package com.org.pizza.domain.entities.drinks;

import com.org.pizza.constant.commonMessages.CommonMessages;
import com.org.pizza.constant.pizzaMessages.PizzaCreationViolationMessages;
import com.org.pizza.domain.entities.BaseEntity;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;

@Entity
@Table(name = "drink_ingredients")
public class DrinkIngredient extends BaseEntity {

    private String ingredientName;
    private BigDecimal ingredientPrice;

    @NotEmpty
    @Column(name = "name", nullable = false, columnDefinition = "VARCHAR (20)")
    @Length(min = 3, max = 20, message = PizzaCreationViolationMessages.INGREDIENT_INCORRECT_NAME_LENGTH)
    public String getIngredientName() {
        return this.ingredientName;
    }

    public void setIngredientName(String ingredientName) {
        this.ingredientName = ingredientName;
    }

    @Column(name = "price", nullable = false)
    @DecimalMin(value = "0.01", message = CommonMessages.MIN_PRICE)
    public BigDecimal getIngredientPrice() {
        return this.ingredientPrice;
    }

    public void setIngredientPrice(BigDecimal ingredientPrice) {
        this.ingredientPrice = ingredientPrice;
    }
}
