package com.org.pizza.repository;

import com.org.pizza.domain.entities.pizza.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@RequestMapping
public interface PizzaRepository extends JpaRepository<Pizza, String> {

    Optional<Pizza> findByName(String name);
}
