package com.org.pizza.service;

import com.org.pizza.domain.entities.pizza.Category;
import com.org.pizza.domain.entities.pizza.Ingredient;
import com.org.pizza.domain.entities.pizza.Pizza;
import com.org.pizza.domain.models.service.CategoryServiceModel;
import com.org.pizza.domain.models.service.IngredientServiceModel;
import com.org.pizza.domain.models.service.PizzaServiceModel;
import com.org.pizza.repository.PizzaRepository;
import com.org.pizza.validation.errors.PizzaAddException;
import com.org.pizza.validation.errors.PizzaAlreadyExistException;
import com.org.pizza.validation.errors.PizzaNotFoundException;
import com.org.pizza.validation.pizzaValidation.PizzaValidationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.org.pizza.constant.commonMessages.CommonMessages.INVALID_DATA_INPUT;
import static com.org.pizza.constant.errorMessages.pizza.PizzaErrorMessages.PIZZA_ALREADY_EXIST;
import static com.org.pizza.constant.errorMessages.pizza.PizzaErrorMessages.PIZZA_NOT_FOUND_EXCEPTION;
import static com.org.pizza.constant.pizzaMessages.PizzaCreationViolationMessages.PIZZA_MISSING_PIC_URL;

@Service
public class PizzaServiceImpl implements PizzaService {

    private final PizzaRepository pizzaRepository;
    private final PizzaValidationService pizzaValidationService;
    private final ModelMapper modelMapper;

    @Autowired
    public PizzaServiceImpl(PizzaRepository pizzaRepository, PizzaValidationService pizzaValidationService, ModelMapper modelMapper) {
        this.pizzaRepository = pizzaRepository;
        this.pizzaValidationService = pizzaValidationService;
        this.modelMapper = modelMapper;
    }

    @Override
    public void addNewPizza(PizzaServiceModel pizzaServiceModel) {

        Pizza pizza = this.pizzaRepository.findByName(pizzaServiceModel.getName())
                .orElse(null);

        if (pizza != null) {
            throw new PizzaAlreadyExistException(PIZZA_ALREADY_EXIST);
        }

        if (this.pizzaValidationService.isValid(pizzaServiceModel)) {
            throw new PizzaAddException(INVALID_DATA_INPUT);
        }
        if (pizzaServiceModel.getImageUrl() == null) {
            pizzaServiceModel
                    .setImageUrl(PIZZA_MISSING_PIC_URL);
        }

        pizza = this.modelMapper.map(pizzaServiceModel, Pizza.class);

        pizza.setCategories(convertCategoriesServiceModelsToCategories(pizzaServiceModel.getCategories()));
        pizza.setIngredients(convertIngredientsServiceModelsToCategories(pizzaServiceModel.getIngredients()));

        BigDecimal pizzaTotalPrice = pizza.getPrice().add(getTotalIngredientsPrice(pizza.getIngredients()));

        pizza.setPrice(pizzaTotalPrice);

        this.pizzaRepository.save(pizza);
    }


    @Override
    public List<PizzaServiceModel> findAll() {
        List<Pizza> pizzas = this.pizzaRepository.findAll();
        List<PizzaServiceModel> pizzaServiceModels = getConvertedPizzaToPizzaServiceModel(pizzas);

        return pizzaServiceModels;
    }


    @Override
    public void deleteById(String id) {
        Pizza pizza = this.pizzaRepository.findById(id)
                .orElseThrow(() -> new PizzaNotFoundException(PIZZA_NOT_FOUND_EXCEPTION));

        this.pizzaRepository.delete(pizza);
    }

    @Override
    public List<PizzaServiceModel> findAllPizzasByCategoryName(String categoryName) {
        List<Pizza> pizzas = this.pizzaRepository
                .findAllByCategoriesContains(categoryName);

        List<PizzaServiceModel> pizzaServiceModels = getConvertedPizzaToPizzaServiceModel(pizzas);

        return pizzaServiceModels;
    }

    @Override
    public LinkedList<PizzaServiceModel> findAllByOrderByName() {
        LinkedList<Pizza> pizzas = this.pizzaRepository.findAllByOrderByName();
        LinkedList<PizzaServiceModel> pizzaServiceModels = pizzas.stream()
                .map(p -> this.modelMapper.map(p, PizzaServiceModel.class))
                .collect(Collectors.toCollection(LinkedList::new));
        return pizzaServiceModels;
    }

    private BigDecimal getTotalIngredientsPrice(Set<Ingredient> ingredients) {
        BigDecimal ingredientsTotalPrice = new BigDecimal(0);

        for (Ingredient ingredient : ingredients) {
            ingredientsTotalPrice = ingredientsTotalPrice.add(ingredient.getIngredientPrice());
        }

        return ingredientsTotalPrice;
    }

    private Set<Ingredient> convertIngredientsServiceModelsToCategories(Set<IngredientServiceModel> ingredients) {
        HashSet<Ingredient> Ingredients = new HashSet<>();
        for (IngredientServiceModel ingredientServiceModel : ingredients) {
            Ingredients.add(this.modelMapper.map(ingredientServiceModel, Ingredient.class));
        }
        return Ingredients;
    }

    private Set<Category> convertCategoriesServiceModelsToCategories(Set<CategoryServiceModel> categoryServiceModels) {

        HashSet<Category> categories = new HashSet<>();
        for (CategoryServiceModel categoryServiceModel : categoryServiceModels) {
            categories.add(this.modelMapper.map(categoryServiceModel, Category.class));
        }
        return categories;
    }

    private List<PizzaServiceModel> getConvertedPizzaToPizzaServiceModel(List<Pizza> pizzas) {
        return pizzas.stream()
                .map(p -> {
                    PizzaServiceModel pizzaServiceModel =
                            this.modelMapper.map(p, PizzaServiceModel.class);
                    convertCategoryToCategoryServiceModel(p, pizzaServiceModel);
                    convertIngredientsToIngredientServiceModel(p, pizzaServiceModel);
                    return pizzaServiceModel;
                })
                .collect(Collectors.toList());
    }

    private void convertIngredientsToIngredientServiceModel(Pizza p, PizzaServiceModel pizzaServiceModel) {
        pizzaServiceModel
                .setIngredients(
                        p.getIngredients()
                                .stream()
                                .map(i -> this.modelMapper.map(i, IngredientServiceModel.class))
                                .collect(Collectors.toSet()));
    }

    private void convertCategoryToCategoryServiceModel(Pizza p, PizzaServiceModel pizzaServiceModel) {
        pizzaServiceModel
                .setCategories(
                        p.getCategories()
                                .stream()
                                .map(c -> this.modelMapper.map(c, CategoryServiceModel.class))
                                .collect(Collectors.toSet()));
    }
}
