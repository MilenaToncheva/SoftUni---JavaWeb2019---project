package com.org.pizza.repository;

import com.org.pizza.domain.entities.pizza.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, String> {

    Optional<Category> findByCategoryName(String categoryName);

    @Query("SELECT c FROM Category AS c ORDER BY c.categoryName ASC")
    LinkedList<Category> findAllOrderByName();
}
