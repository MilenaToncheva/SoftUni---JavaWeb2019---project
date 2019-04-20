package com.org.pizza.web.controller.moderator;

import com.org.pizza.domain.models.binding.PizzaAddBindingModel;
import com.org.pizza.domain.models.service.CategoryServiceModel;
import com.org.pizza.domain.models.service.IngredientServiceModel;
import com.org.pizza.service.CategoryService;
import com.org.pizza.service.IngredientService;
import com.org.pizza.service.PizzaService;
import com.org.pizza.web.controller.BaseController;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

import static com.org.pizza.constant.commonMessages.CommonMessages.BINDING_MODEL;


@Controller
@RequestMapping("/pizzas")
public class PizzaController extends BaseController {

    private static final String INGREDIENTS = "ingredients";
    private static final String CATEGORIES = "categories";

    private final PizzaService pizzaService;
    private final IngredientService ingredientService;
    private final CategoryService categoryService;
    private final ModelMapper modelMapper;

    @Autowired
    public PizzaController(PizzaService pizzaService, IngredientService ingredientService, CategoryService categoryService, ModelMapper modelMapper) {
        this.pizzaService = pizzaService;
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
        List<CategoryServiceModel> categories = this.categoryService.findAllCategories();

        modelAndView.addObject(CATEGORIES, categories);
        modelAndView.addObject(INGREDIENTS, ingredients);
        modelAndView.addObject(BINDING_MODEL, bindingModel);
        return view("moderator/pizza-add", modelAndView);
    }

//    @PostMapping("/add")
//    @PreAuthorize("hasRole('ROLE_MODERATOR')")
//    public ModelAndView addPizzaConfirm(
//            @Valid @ModelAttribute(name = BINDING_MODEL) PizzaAddBindingModel bindingModel,
//            BindingResult bindingResult, ModelAndView modelAndView) {
//
//        if (bindingResult.hasErrors()) {
//            modelAndView.addObject(BINDING_MODEL, bindingModel);
//            return view("moderator/pizza-add");
//        }
//
//        return redirect("pizzas/all");
//    }
}
