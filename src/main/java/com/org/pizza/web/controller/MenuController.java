package com.org.pizza.web.controller;

import com.org.pizza.domain.models.service.CategoryServiceModel;
import com.org.pizza.domain.models.service.DrinkServiceModel;
import com.org.pizza.domain.models.service.PizzaServiceModel;
import com.org.pizza.domain.models.view.CategoryViewModel;
import com.org.pizza.domain.models.view.DrinkAllViewModel;
import com.org.pizza.domain.models.view.DrinkMenuViewModel;
import com.org.pizza.domain.models.view.PizzaMenuViewModel;
import com.org.pizza.service.CategoryService;
import com.org.pizza.service.DrinkService;
import com.org.pizza.service.PizzaService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import static com.org.pizza.constant.commonMessages.CommonMessages.BINDING_MODEL;

@Controller
public class MenuController extends BaseController {

    private static final String CATEGORIES = "categories";

    private final PizzaService pizzaService;
    private final CategoryService categoryService;
    private final DrinkService drinkService;
    private final ModelMapper modelMapper;

    @Autowired
    public MenuController(PizzaService pizzaService, CategoryService categoryService, DrinkService drinkService, ModelMapper modelMapper) {
        this.pizzaService = pizzaService;
        this.categoryService = categoryService;
        this.drinkService = drinkService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/menu")
    public ModelAndView menu(ModelAndView modelAndView) {
        LinkedList<CategoryServiceModel> categoryServiceModels = this.categoryService.findAllOrderByName();
        LinkedList<CategoryViewModel> categoryViewModels = getConvertedCategoriesServiceModelToCategoriesViewModel(categoryServiceModels);

        LinkedList<PizzaServiceModel> pizzaServiceModels = this.pizzaService.findAllByOrderByName();
        LinkedList<PizzaMenuViewModel> pizzaMenuViewModels = getConvertedPizzaServiceModelToPizzaMenuViewModel(pizzaServiceModels);
        modelAndView.addObject(BINDING_MODEL, pizzaMenuViewModels);
        modelAndView.addObject(CATEGORIES, categoryViewModels);
        return view("menu", modelAndView);
    }

    @ResponseBody
    @GetMapping("/menu/drinks")
    public List<DrinkMenuViewModel> getDrinks() {
        List<DrinkServiceModel> drinkServiceModels = this.drinkService.findAllByOrderByName();
        List<DrinkMenuViewModel> drinkMenuViewModels = drinkServiceModels.stream()
                .map(d -> this.modelMapper.map(d, DrinkMenuViewModel.class))
                .collect(Collectors.toList());

        return drinkMenuViewModels;
    }

//    @GetMapping("/menu")
//    public ModelAndView menu(ModelAndView modelAndView) {
//        LinkedList<PizzaServiceModel> pizzaServiceModels = this.pizzaService.findAllByOrderByName();
//        LinkedList<PizzaMenuViewModel> pizzaMenuViewModels =getConvertedPizzaServiceModelToPizzaMenuViewModel(pizzaServiceModels);
//        modelAndView.addObject(BINDING_MODEL, pizzaMenuViewModels);
//        return view("menu", modelAndView);
//    }

//    @ResponseBody
//    @GetMapping("/menu/spicy")
//    public List<PizzaMenuViewModel> showSpicyPizzas(@RequestParam String category) {
//        List<PizzaServiceModel> pizzaServiceModels = this.pizzaService.findAllPizzasByCategoryName(category);
//        List<PizzaMenuViewModel> spicyPizzas = getConvertedPizzaServiceModelToPizzaMenuViewModel(pizzaServiceModels);
//        return spicyPizzas;
//    }
//
//    @ResponseBody
//    @GetMapping("/menu/vegetarian")
//    public List<PizzaMenuViewModel> showVegetarianPizzas(@RequestParam String category) {
//        List<PizzaServiceModel> pizzaServiceModels = this.pizzaService.findAllPizzasByCategoryName(category);
//        List<PizzaMenuViewModel> vegetarianPizzas = getConvertedPizzaServiceModelToPizzaMenuViewModel(pizzaServiceModels);
//        return vegetarianPizzas;
//    }
//
//
//    @ResponseBody
//    @GetMapping("/menu/all")
//    public List<PizzaMenuViewModel> showAllPizza(@RequestParam String category) {
//        List<PizzaServiceModel> pizzaServiceModels = this.pizzaService.findAllPizzasByCategoryName(category);
//        List<PizzaMenuViewModel> allPizzas = getConvertedPizzaServiceModelToPizzaMenuViewModel(pizzaServiceModels);
//        return allPizzas;
//    }

    private LinkedList<PizzaMenuViewModel> getConvertedPizzaServiceModelToPizzaMenuViewModel(LinkedList<PizzaServiceModel> pizzaServiceModels) {
        return pizzaServiceModels.stream()
                .map(p -> this.modelMapper.map(p, PizzaMenuViewModel.class))
                .collect(Collectors.toCollection(LinkedList::new));
    }


    private LinkedList<CategoryViewModel> getConvertedCategoriesServiceModelToCategoriesViewModel(LinkedList<CategoryServiceModel> categoryServiceModels) {
        return categoryServiceModels.stream()
                .map(c -> this.modelMapper.map(c, CategoryViewModel.class))
                .collect(Collectors.toCollection(LinkedList::new));
    }
}
