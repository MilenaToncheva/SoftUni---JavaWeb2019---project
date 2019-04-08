package com.org.pizza.domain.entities.pizza;

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
@Table(name = "ingredients")
public class Ingredient extends BaseEntity {

    private String name;
    private BigDecimal price;

    @NotEmpty
    @Column(name = "name", nullable = false, columnDefinition = "VARCHAR (20)")
    @Length(min = 3, max = 20, message = PizzaCreationViolationMessages.INGREDIENT_INCORRECT_NAME_LENGTH)
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NotEmpty
    @Column(name = "price", nullable = false)
    @DecimalMin(value = "0.01", message = CommonMessages.MIN_PRICE)
    public BigDecimal getPrice() {
        return this.price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
