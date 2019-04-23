package com.org.pizza.repository;

import com.org.pizza.domain.entities.pizza.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Repository
public interface PizzaRepository extends JpaRepository<Pizza, String> {

    Optional<Pizza> findByName(String name);

    List<Pizza> findAllByCategoriesContains(String categoryName);

    LinkedList<Pizza> findAllByOrderByName();
}
