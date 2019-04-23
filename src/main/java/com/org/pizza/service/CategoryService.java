package com.org.pizza.service;

import com.org.pizza.domain.models.service.CategoryServiceModel;

import java.util.LinkedList;
import java.util.List;

public interface CategoryService {

    void addNewCategory(CategoryServiceModel categoryServiceModel);

    List<CategoryServiceModel> findAllCategories();

    LinkedList<CategoryServiceModel> findAllOrderByName();

    void deleteById(String id);

    CategoryServiceModel findById(String id);
}
