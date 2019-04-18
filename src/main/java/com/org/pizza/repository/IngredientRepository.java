package com.org.pizza.repository;

import com.org.pizza.domain.entities.pizza.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, String> {
    Optional<Ingredient> findByIngredientName(String name);
}
