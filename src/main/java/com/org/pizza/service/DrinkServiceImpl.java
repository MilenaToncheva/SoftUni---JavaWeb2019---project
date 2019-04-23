package com.org.pizza.service;

import com.org.pizza.domain.entities.drinks.Drink;
import com.org.pizza.domain.entities.drinks.DrinkIngredient;
import com.org.pizza.domain.models.service.DrinkServiceModel;
import com.org.pizza.repository.DrinkRepository;
import com.org.pizza.validation.drinkValidation.DrinkValidationService;
import com.org.pizza.validation.errors.DrinkAddException;
import com.org.pizza.validation.errors.DrinkAlreadyExistException;
import com.org.pizza.validation.errors.DrinkNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.Set;
import java.util.stream.Collectors;

import static com.org.pizza.constant.commonMessages.CommonMessages.INVALID_DATA_INPUT;
import static com.org.pizza.constant.drinkMessages.DrinkCreationViolationMessages.DRINK_MISSING_PIC_URL;
import static com.org.pizza.constant.errorMessages.drink.DrinkErrorMessages.DRINK_ALREADY_EXIST;
import static com.org.pizza.constant.errorMessages.drink.DrinkErrorMessages.DRINK_NOT_FOUND_EXCEPTION;

@Service
public class DrinkServiceImpl implements DrinkService {
    private final DrinkRepository drinkRepository;
    private final ModelMapper modelMapper;
    private final DrinkValidationService drinkValidationService;

    @Autowired
    public DrinkServiceImpl(DrinkRepository drinkRepository, ModelMapper modelMapper,
                            DrinkValidationService drinkValidationService) {
        this.drinkRepository = drinkRepository;
        this.modelMapper = modelMapper;
        this.drinkValidationService = drinkValidationService;
    }

    @Override
    public void addNewDrink(DrinkServiceModel drinkServiceModel) {
        Drink drink = this.drinkRepository.findByName(drinkServiceModel.getName())
                .orElse(null);

        if (drink != null) {
            throw new DrinkAlreadyExistException(DRINK_ALREADY_EXIST);
        }

        if (this.drinkValidationService.isValid(drinkServiceModel)) {
            throw new DrinkAddException(INVALID_DATA_INPUT);
        }
        if (drinkServiceModel.getImageUrl() == null) {
            drinkServiceModel
                    .setImageUrl(DRINK_MISSING_PIC_URL);
        }

        drink = this.modelMapper.map(drinkServiceModel, Drink.class);
        BigDecimal drinkTotalPrice = drink.getPrice().add(getTotalIngredientsPrice(drink.getIngredients()));
        drink.setPrice(drinkTotalPrice);

        this.drinkRepository.save(drink);
    }


    @Override
    public LinkedList<DrinkServiceModel> findAllByOrderByName() {
        LinkedList<Drink> drinks = this.drinkRepository.findAllByOrderByName();
        LinkedList<DrinkServiceModel> drinkServiceModels = drinks.stream()
                .map(d -> this.modelMapper.map(d, DrinkServiceModel.class))
                .collect(Collectors.toCollection(LinkedList::new));
        return drinkServiceModels;
    }

    @Override
    public void deleteById(String id) {
        Drink drink = this.drinkRepository.findById(id)
                .orElseThrow(() -> new DrinkNotFoundException(DRINK_NOT_FOUND_EXCEPTION));

        this.drinkRepository.delete(drink);
    }

    private BigDecimal getTotalIngredientsPrice(Set<DrinkIngredient> ingredients) {
        BigDecimal ingredientsTotalPrice = new BigDecimal(0);

        for (DrinkIngredient ingredient : ingredients) {
            ingredientsTotalPrice = ingredientsTotalPrice.add(ingredient.getIngredientPrice());
        }

        return ingredientsTotalPrice;
    }
}
