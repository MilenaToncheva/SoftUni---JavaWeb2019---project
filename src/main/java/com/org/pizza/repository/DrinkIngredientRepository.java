package com.org.pizza.repository;

import com.org.pizza.domain.entities.drinks.DrinkIngredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface DrinkIngredientRepository extends JpaRepository<DrinkIngredient, String> {

    Optional<DrinkIngredient> findByIngredientName(String name);
}
