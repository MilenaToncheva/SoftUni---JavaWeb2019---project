package com.org.pizza.domain.entities;

import com.org.pizza.domain.entities.drinks.Drink;
import com.org.pizza.domain.entities.pizza.Pizza;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "pizza_orders")
public class Order extends BaseEntity {

    private List<Pizza> pizzas;
    private List<Drink> drinks;
    private User customer;
    private BigDecimal totalPrice;
    private LocalDateTime orderPlacedOn;
    private LocalDateTime expectedDeliveryTime;

    public Order() {
    }

    @ManyToMany(targetEntity = Pizza.class)
    @JoinTable(
            joinColumns = @JoinColumn(name = "order_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "pizza_id", referencedColumnName = "id")
    )
    public List<Pizza> getPizzas() {
        return this.pizzas;
    }

    public void setPizzas(List<Pizza> pizzas) {
        this.pizzas = pizzas;
    }

    @ManyToMany(targetEntity = Drink.class)
    @JoinTable(
            joinColumns = @JoinColumn(name = "order_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "drink_id", referencedColumnName = "id")
    )
    public List<Drink> getDrinks() {
        return this.drinks;
    }

    public void setDrinks(List<Drink> drinks) {
        this.drinks = drinks;
    }

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    public User getCustomer() {
        return this.customer;
    }

    public void setCustomer(User customer) {
        this.customer = customer;
    }

    @Column(name = "total_price")
    public BigDecimal getTotalPrice() {
        return this.totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Column(name = "order_placed_on")
    public LocalDateTime getOrderPlacedOn() {
        return this.orderPlacedOn;
    }

    public void setOrderPlacedOn(LocalDateTime orderPlacedOn) {
        this.orderPlacedOn = orderPlacedOn;
    }

    @Column(name = "expected_delivery_time")
    public LocalDateTime getExpectedDeliveryTime() {
        return this.expectedDeliveryTime;
    }

    public void setExpectedDeliveryTime(LocalDateTime expectedDeliveryTime) {
        this.expectedDeliveryTime = expectedDeliveryTime;
    }
}
