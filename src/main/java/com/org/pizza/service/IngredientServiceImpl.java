package com.org.pizza.service;

import com.org.pizza.domain.entities.pizza.Ingredient;
import com.org.pizza.domain.models.service.IngredientServiceModel;
import com.org.pizza.repository.IngredientRepository;
import com.org.pizza.validation.errors.IngredientAddException;
import com.org.pizza.validation.errors.IngredientAlreadyExistException;
import com.org.pizza.validation.errors.IngredientNotFoundException;
import com.org.pizza.validation.ingredientValidation.IngredientValidationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.org.pizza.constant.commonMessages.CommonMessages.INVALID_DATA_INPUT;
import static com.org.pizza.constant.errorMessages.ingredient.IngredientErrorMessages.INGREDIENT_ALREADY_EXIST;
import static com.org.pizza.constant.errorMessages.ingredient.IngredientErrorMessages.INGREDIENT_NOT_FOUND_EXCEPTION;

@Service
public class IngredientServiceImpl implements IngredientService {

    private final IngredientRepository ingredientRepository;
    private final IngredientValidationService ingredientValidationService;
    private final ModelMapper modelMapper;

    @Autowired
    public IngredientServiceImpl(IngredientRepository ingredientRepository,
                                 IngredientValidationService ingredientValidationService, ModelMapper modelMapper) {
        this.ingredientRepository = ingredientRepository;
        this.ingredientValidationService = ingredientValidationService;
        this.modelMapper = modelMapper;
    }

    @Override
    public void addNewIngredient(IngredientServiceModel ingredientServiceModel) {

        Ingredient ingredient = this.ingredientRepository.
                findByIngredientName(ingredientServiceModel.getIngredientName())
                .orElse(null);

        if (ingredient != null) {
            throw new IngredientAlreadyExistException(INGREDIENT_ALREADY_EXIST);
        }

        if (this.ingredientValidationService.isValid(ingredientServiceModel)) {
            throw new IngredientAddException(INVALID_DATA_INPUT);
        }

        ingredient = this.modelMapper.map(ingredientServiceModel, Ingredient.class);
        this.ingredientRepository.save(ingredient);
    }

    @Override
    public void editIngredient(IngredientServiceModel ingredientServiceModel, String id) {

        Ingredient ingredient = this.ingredientRepository.
                findById(id)
                .orElseThrow(() -> new IngredientNotFoundException(INGREDIENT_NOT_FOUND_EXCEPTION));

        if (!this.ingredientValidationService.isValid(ingredientServiceModel)) {
            throw new IngredientAddException(INVALID_DATA_INPUT);
        }

        ingredient.setIngredientName(ingredientServiceModel.getIngredientName());
        ingredient.setIngredientName(ingredientServiceModel.getIngredientName());

        this.ingredientRepository.save(ingredient);
    }

    @Override
    public IngredientServiceModel findIngredientById(String id) {

        Ingredient ingredient = this.ingredientRepository.findById(id)
                .orElseThrow(() -> new IngredientNotFoundException(INGREDIENT_NOT_FOUND_EXCEPTION));

        IngredientServiceModel ingredientServiceModel = this.modelMapper.map(ingredient, IngredientServiceModel.class);
        return ingredientServiceModel;
    }

    @Override
    public List<IngredientServiceModel> findAllIngredients() {
        List<Ingredient> ingredients = this.ingredientRepository.findAll();
        List<IngredientServiceModel> ingredientServiceModels = ingredients
                .stream()
                .map(i -> this.modelMapper.map(i, IngredientServiceModel.class))
                .collect(Collectors.toList());

        return ingredientServiceModels;
    }
}
