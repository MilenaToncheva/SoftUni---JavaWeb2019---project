package com.org.pizza.repository;

import com.org.pizza.domain.entities.drinks.Drink;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;
import java.util.Optional;

@Repository
public interface DrinkRepository extends JpaRepository<Drink, String> {
    Optional<Drink> findByName(String name);

    LinkedList<Drink> findAllByOrderByName();
}
