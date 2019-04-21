package com.org.pizza.web.controller.moderator;

import com.org.pizza.domain.models.binding.PizzaAddBindingModel;
import com.org.pizza.domain.models.service.CategoryServiceModel;
import com.org.pizza.domain.models.service.IngredientServiceModel;
import com.org.pizza.domain.models.service.PizzaServiceModel;
import com.org.pizza.domain.models.view.PizzaAllViewModel;
import com.org.pizza.service.CategoryService;
import com.org.pizza.service.CloudinaryService;
import com.org.pizza.service.IngredientService;
import com.org.pizza.service.PizzaService;
import com.org.pizza.web.controller.BaseController;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.org.pizza.constant.commonMessages.CommonMessages.BINDING_MODEL;


@Controller
@RequestMapping("/pizzas")
public class PizzaController extends BaseController {

    private static final String INGREDIENTS = "ingredients";
    private static final String CATEGORIES = "categories";

    private final PizzaService pizzaService;
    private final CloudinaryService cloudinaryService;
    private final IngredientService ingredientService;
    private final CategoryService categoryService;
    private final ModelMapper modelMapper;

    @Autowired
    public PizzaController(PizzaService pizzaService, CloudinaryService cloudinaryService,
                           IngredientService ingredientService,
                           CategoryService categoryService, ModelMapper modelMapper) {
        this.pizzaService = pizzaService;
        this.cloudinaryService = cloudinaryService;
        this.ingredientService = ingredientService;
        this.categoryService = categoryService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/add")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public ModelAndView addPizza(
            @ModelAttribute(name = BINDING_MODEL) PizzaAddBindingModel bindingModel,
            ModelAndView modelAndView) {

        List<IngredientServiceModel> ingredients = this.ingredientService.findAllIngredients();
        setCategoryViewList(modelAndView);
        modelAndView.addObject(INGREDIENTS, ingredients);
        modelAndView.addObject(BINDING_MODEL, bindingModel);
        return view("moderator/pizza-add", modelAndView);
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public ModelAndView addPizzaConfirm(
            @Valid @ModelAttribute(name = BINDING_MODEL) PizzaAddBindingModel bindingModel,
            @RequestParam(value = "categories", required = false) List<String> categoriesId,
            @RequestParam(value = "ingredients", required = false) List<String> ingredientsId,
            BindingResult bindingResult, ModelAndView modelAndView) throws IOException {


        PizzaServiceModel pizzaServiceModel = this.modelMapper.map(bindingModel, PizzaServiceModel.class);
        pizzaServiceModel.setCategories(getChosenCategories(categoriesId));
        pizzaServiceModel.setIngredients(getChosenIngredients(ingredientsId));

        if (!bindingModel.getImage().isEmpty()) {
            pizzaServiceModel.setImageUrl(
                    this.cloudinaryService.uploadImage(bindingModel.getImage()));
        }

        if (bindingResult.hasErrors()) {
            setCategoryViewList(modelAndView);
            setIngredientsViewList(modelAndView);
            modelAndView.addObject(BINDING_MODEL, bindingModel);
            return view("moderator/pizza-add");
        }


        this.pizzaService.addNewPizza(pizzaServiceModel);
        return redirect("/pizzas/all");
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public ModelAndView allPizza(ModelAndView modelAndView) {

        List<PizzaServiceModel> pizzaServiceModels = this.pizzaService.findAll();
        List<PizzaAllViewModel> pizzaAllViewModels = setPizzaAllViewModels(pizzaServiceModels);

        modelAndView.addObject(BINDING_MODEL, pizzaAllViewModels);
        return view("moderator/pizza-all", modelAndView);
    }

    @PostMapping("/delete/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    private List<PizzaAllViewModel> setPizzaAllViewModels(List<PizzaServiceModel> pizzaServiceModels) {
        return pizzaServiceModels.stream()
                .map(p -> {
                    PizzaAllViewModel pizzaAllViewModel =
                            this.modelMapper.map(p, PizzaAllViewModel.class);
                    pizzaAllViewModel.setCategories(p.getCategories());
                    pizzaAllViewModel.setIngredients(p.getIngredients());
                    return pizzaAllViewModel;
                })
                .collect(Collectors.toList());
    }


    private void setIngredientsViewList(ModelAndView modelAndView) {
        List<IngredientServiceModel> ingredientsList = this.ingredientService.findAllIngredients();
        modelAndView.addObject(INGREDIENTS, ingredientsList);
    }

    private void setCategoryViewList(ModelAndView modelAndView) {
        List<CategoryServiceModel> categoriesList = this.categoryService.findAllCategories();
        modelAndView.addObject(CATEGORIES, categoriesList);
    }

    private Set<IngredientServiceModel> getChosenIngredients(@RequestParam(value = "ingredients", required = false) List<String> ingredientsId) {
        Set<IngredientServiceModel> ingredients = new HashSet<>();
        for (String id : ingredientsId) {
            IngredientServiceModel ingredient = this.ingredientService.findIngredientById(id);
            ingredients.add(ingredient);
        }
        return ingredients;
    }

    private Set<CategoryServiceModel> getChosenCategories(@RequestParam(value = "categories", required = false) List<String> categoriesId) {
        Set<CategoryServiceModel> categories = new HashSet<>();
        for (String id : categoriesId) {
            CategoryServiceModel category = this.categoryService.findById(id);
            categories.add(category);
        }
        return categories;
    }


}
