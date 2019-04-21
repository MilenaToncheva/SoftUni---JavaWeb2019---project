package com.org.pizza.domain.entities.pizza;

import com.org.pizza.constant.pizzaMessages.PizzaCreationViolationMessages;
import com.org.pizza.domain.entities.BaseEntity;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "categories")
public class Category extends BaseEntity {

    private String categoryName;


    @NotEmpty
    @Column(name = "category_name", unique = true, nullable = false, columnDefinition = "VARCHAR (20)")
    @Length(min = 3, max = 20, message = PizzaCreationViolationMessages.CATEGORY_INCORRECT_NAME_LENGTH)
    public String getCategoryName() {
        return this.categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
