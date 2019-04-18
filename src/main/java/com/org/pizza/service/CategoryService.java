package com.org.pizza.service;

import com.org.pizza.domain.models.service.CategoryServiceModel;

import java.util.List;

public interface CategoryService {

    void addNewCategory(CategoryServiceModel categoryServiceModel);

    List<CategoryServiceModel> findAllCategories();

    void deleteById(String id);
}
