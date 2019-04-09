package com.org.pizza.domain.entities.pizza;

import com.org.pizza.constant.commonMessages.CommonMessages;
import com.org.pizza.constant.pizzaMessages.PizzaCreationViolationMessages;
import com.org.pizza.domain.entities.BaseEntity;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;
import java.util.Set;


@Entity
@Table(name = "pizzas")
public class Pizza extends BaseEntity {

    private String name;
    private BigDecimal price;
    private String imageUrl;
    private Double grams;
    private Set<Ingredient> ingredients;
    private Set<Category> categories;

    @NotEmpty
    @Column(name = "name", nullable = false, unique = true, columnDefinition = "VARCHAR (20)")
    @Length(min = 3, max = 20, message = PizzaCreationViolationMessages.PIZZA_INCORRECT_NAME_LENGTH)
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

    @NotEmpty
    @Column(name = "grams", nullable = false)
   @DecimalMin(value = "0.01", message = PizzaCreationViolationMessages.PIZZA_INCORRECT_GRAM_VALUE)
    public Double getGrams() {
        return this.grams;
    }

    public void setGrams(Double grams) {
        this.grams = grams;
    }

    @ManyToMany(targetEntity = Ingredient.class)
    @JoinTable(name = "pizza_category",
            joinColumns = @JoinColumn(name = "pizza_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "category_id", referencedColumnName = "id"))
    public Set<Ingredient> getIngredients() {
        return this.ingredients;
    }

    public void setIngredients(Set<Ingredient> toppings) {
        this.ingredients = toppings;
    }

    @OneToMany
    public Set<Category> getCategories() {
        return this.categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

//    @Column(name = "size", nullable = false)
//    @Enumerated(EnumType.STRING)
//    public Size getSize() {
//        return this.size;
//    }
//
//    public void setSize(Size size) {
//        this.size = size;
//    }
//
//    @Column(name = "dough", nullable = false)
//    @Enumerated(EnumType.STRING)
//    public Dough getDoughType() {
//        return this.doughType;
//    }
//
//    public void setDoughType(Dough doughType) {
//        this.doughType = doughType;
//    }
}
