package com.org.pizza.service;

import com.org.pizza.domain.entities.drinks.DrinkIngredient;
import com.org.pizza.domain.models.service.DrinkIngredientServiceModel;
import com.org.pizza.repository.DrinkIngredientRepository;
import com.org.pizza.validation.drinkIngredientValidation.DrinkIngredientValidationService;
import com.org.pizza.validation.errors.DrinkIngredientAddException;
import com.org.pizza.validation.errors.DrinkIngredientAlreadyExistException;
import com.org.pizza.validation.errors.DrinkIngredientNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.org.pizza.constant.commonMessages.CommonMessages.INVALID_DATA_INPUT;
import static com.org.pizza.constant.errorMessages.ingredient.IngredientErrorMessages.INGREDIENT_ALREADY_EXIST;
import static com.org.pizza.constant.errorMessages.ingredient.IngredientErrorMessages.INGREDIENT_NOT_FOUND_EXCEPTION;

@Service
public class DrinkIngredientServiceImpl implements DrinkIngredientService {

    private final DrinkIngredientRepository drinkIngredientRepository;
    private final DrinkIngredientValidationService drinkIngredientValidationService;
    private final ModelMapper modelMapper;

    @Autowired
    public DrinkIngredientServiceImpl(DrinkIngredientRepository drinkIngredientRepository, DrinkIngredientValidationService drinkIngredientValidationService, ModelMapper modelMapper) {
        this.drinkIngredientRepository = drinkIngredientRepository;
        this.drinkIngredientValidationService = drinkIngredientValidationService;
        this.modelMapper = modelMapper;
    }

    @Override
    public void addNewIngredient(DrinkIngredientServiceModel drinkIngredientServiceModel) {
        DrinkIngredient drinkIngredient = this.drinkIngredientRepository.
                findByIngredientName(drinkIngredientServiceModel.getIngredientName())
                .orElse(null);

        if (drinkIngredient != null) {
            throw new DrinkIngredientAlreadyExistException(INGREDIENT_ALREADY_EXIST);
        }

        if (this.drinkIngredientValidationService.isValid(drinkIngredientServiceModel)) {
            throw new DrinkIngredientAddException(INVALID_DATA_INPUT);
        }

        drinkIngredient = this.modelMapper.map(drinkIngredientServiceModel, DrinkIngredient.class);
        this.drinkIngredientRepository.save(drinkIngredient);
    }

    @Override
    public void editIngredient(DrinkIngredientServiceModel drinkIngredientServiceModel) {
        DrinkIngredient drinkIngredient = this.drinkIngredientRepository.
                findById(drinkIngredientServiceModel.getId())
                .orElseThrow(() -> new DrinkIngredientNotFoundException(INGREDIENT_NOT_FOUND_EXCEPTION));

        if (this.drinkIngredientValidationService.isValid(drinkIngredientServiceModel)) {
            throw new DrinkIngredientAddException(INVALID_DATA_INPUT);
        }

        drinkIngredient.setIngredientName(drinkIngredientServiceModel.getIngredientName());
        drinkIngredient.setIngredientPrice(drinkIngredientServiceModel.getIngredientPrice());

        this.drinkIngredientRepository.save(drinkIngredient);
    }

    @Override
    public DrinkIngredientServiceModel findIngredientById(String id) {
        DrinkIngredient drinkIngredient = this.drinkIngredientRepository.findById(id)
                .orElseThrow(() -> new DrinkIngredientNotFoundException(INGREDIENT_NOT_FOUND_EXCEPTION));

        DrinkIngredientServiceModel drinkIngredientServiceModel = this.modelMapper.map(drinkIngredient, DrinkIngredientServiceModel.class);
        return drinkIngredientServiceModel;
    }

    @Override
    public Set<DrinkIngredientServiceModel> findAllDrinkIngredients() {
        List<DrinkIngredient> ingredients = this.drinkIngredientRepository.findAll();
        Set<DrinkIngredientServiceModel> drinkIngredientServiceModels = ingredients
                .stream()
                .map(i -> this.modelMapper.map(i, DrinkIngredientServiceModel.class))
                .collect(Collectors.toSet());

        return drinkIngredientServiceModels;
    }

    @Override
    public void deleteById(String id) {
        DrinkIngredient drinkIngredient = this.drinkIngredientRepository.findById(id)
                .orElseThrow(() -> new DrinkIngredientNotFoundException(INGREDIENT_NOT_FOUND_EXCEPTION));

        this.drinkIngredientRepository.delete(drinkIngredient);
    }
}
