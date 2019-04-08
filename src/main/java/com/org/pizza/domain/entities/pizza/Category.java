package com.org.pizza.domain.entities.pizza;

import com.org.pizza.constant.pizzaMessages.PizzaCreationViolationMessages;
import com.org.pizza.domain.entities.BaseEntity;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "categories")
public class Category extends BaseEntity {

private String name;


    @Column(name = "category_name", nullable = false, unique = true, columnDefinition = "VARCHAR (20)")
    @Length(min = 3, max = 20, message = PizzaCreationViolationMessages.CATEGORY_INCORRECT_NAME_LENGTH)
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
