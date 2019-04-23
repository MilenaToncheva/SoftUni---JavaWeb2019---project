package com.org.pizza.service;

import com.org.pizza.domain.entities.pizza.Category;
import com.org.pizza.domain.models.service.CategoryServiceModel;
import com.org.pizza.repository.CategoryRepository;
import com.org.pizza.validation.categoryValidation.CategoryValidationService;
import com.org.pizza.validation.errors.CategoryAddException;
import com.org.pizza.validation.errors.CategoryAlreadyExistException;
import com.org.pizza.validation.errors.CategoryNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import static com.org.pizza.constant.commonMessages.CommonMessages.INVALID_DATA_INPUT;
import static com.org.pizza.constant.errorMessages.category.CategoryErrorMessages.CATEGORY_ALREADY_EXIST;
import static com.org.pizza.constant.errorMessages.category.CategoryErrorMessages.CATEGORY_NOT_FOUND_EXCEPTION;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;
    private final CategoryValidationService categoryValidationService;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository, ModelMapper modelMapper, CategoryValidationService categoryValidationService) {
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
        this.categoryValidationService = categoryValidationService;
    }

    @Override
    public void addNewCategory(CategoryServiceModel categoryServiceModel) {

        Category category = this.categoryRepository.
                findByCategoryName(categoryServiceModel.getCategoryName())
                .orElse(null);

        if (category != null) {
            throw new CategoryAlreadyExistException(CATEGORY_ALREADY_EXIST);
        }

        if (this.categoryValidationService.isValid(categoryServiceModel)) {
            throw new CategoryAddException(INVALID_DATA_INPUT);
        }

        category = this.modelMapper.map(categoryServiceModel, Category.class);

        this.categoryRepository.save(category);
    }

    @Override
    public List<CategoryServiceModel> findAllCategories() {
        List<Category> categories = this.categoryRepository.findAll();
        List<CategoryServiceModel> categoryServiceModels = categories
                .stream()
                .map(c -> this.modelMapper.map(c, CategoryServiceModel.class))
                .collect(Collectors.toList());
        return categoryServiceModels;
    }

    @Override
    public LinkedList<CategoryServiceModel> findAllOrderByName() {
        LinkedList<Category> categories = this.categoryRepository.findAllOrderByName();
        LinkedList<CategoryServiceModel> categoryServiceModels = categories.stream()
                .map(c -> this.modelMapper.map(c, CategoryServiceModel.class))
                .collect(Collectors.toCollection(LinkedList::new));
        return categoryServiceModels;
    }

    @Override
    public void deleteById(String id) {
        Category category = this.categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException(CATEGORY_NOT_FOUND_EXCEPTION));

        this.categoryRepository.delete(category);
    }

    @Override
    public CategoryServiceModel findById(String id) {
        Category category = this.categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException(CATEGORY_NOT_FOUND_EXCEPTION));
        CategoryServiceModel categoryServiceModel = this.modelMapper.map(category, CategoryServiceModel.class);
        return categoryServiceModel;
    }
}
