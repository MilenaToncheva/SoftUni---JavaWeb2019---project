package com.org.pizza.web.controller.moderator;

import com.org.pizza.domain.models.binding.DrinkAddBindingModel;
import com.org.pizza.domain.models.service.DrinkIngredientServiceModel;
import com.org.pizza.domain.models.service.DrinkServiceModel;
import com.org.pizza.domain.models.view.DrinkAllViewModel;
import com.org.pizza.service.CloudinaryService;
import com.org.pizza.service.DrinkIngredientService;
import com.org.pizza.service.DrinkService;
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
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.org.pizza.constant.commonMessages.CommonMessages.BINDING_MODEL;

@Controller
@RequestMapping("/drinks")
public class DrinkController extends BaseController {

    private static final String INGREDIENTS = "ingredients";


    private final DrinkService drinkService;
    private final DrinkIngredientService drinkIngredientService;
    private final CloudinaryService cloudinaryService;
    private final ModelMapper modelMapper;

    @Autowired
    public DrinkController(DrinkService drinkService, DrinkIngredientService drinkIngredientService, CloudinaryService cloudinaryService, ModelMapper modelMapper) {
        this.drinkService = drinkService;
        this.drinkIngredientService = drinkIngredientService;
        this.cloudinaryService = cloudinaryService;
        this.modelMapper = modelMapper;
    }


    @GetMapping("/add")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public ModelAndView addDrink(
            @ModelAttribute(name = BINDING_MODEL) DrinkAddBindingModel bindingModel,
            ModelAndView modelAndView) {

        Set<DrinkIngredientServiceModel> ingredients = this.drinkIngredientService.findAllDrinkIngredients();
        modelAndView.addObject(INGREDIENTS, ingredients);
        modelAndView.addObject(BINDING_MODEL, bindingModel);
        return view("moderator/drink-add", modelAndView);
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public ModelAndView addDrinkConfirm(
            @Valid @ModelAttribute(name = BINDING_MODEL) DrinkAddBindingModel bindingModel,
            @RequestParam(value = "ingredients", required = false) List<String> ingredientsId,
            BindingResult bindingResult, ModelAndView modelAndView) throws IOException {


        DrinkServiceModel drinkServiceModel = this.modelMapper.map(bindingModel, DrinkServiceModel.class);
        drinkServiceModel.setIngredients(getChosenIngredients(ingredientsId));

        if (!bindingModel.getImage().isEmpty()) {
            drinkServiceModel.setImageUrl(
                    this.cloudinaryService.uploadImage(bindingModel.getImage()));
        }

        if (bindingResult.hasErrors()) {
            setIngredientsViewList(modelAndView);
            modelAndView.addObject(BINDING_MODEL, bindingModel);
            return view("moderator/drink-add");
        }

        this.drinkService.addNewDrink(drinkServiceModel);
        return redirect("/drinks/all");
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public ModelAndView allDrinks(ModelAndView modelAndView) {

        LinkedList<DrinkServiceModel> drinkServiceModels = this.drinkService.findAllByOrderByName();
        LinkedList<DrinkAllViewModel> drinkAllViewModels = setDrinkAllViewModels(drinkServiceModels);

        modelAndView.addObject(BINDING_MODEL, drinkAllViewModels);
        return view("moderator/drink-all", modelAndView);
    }

    @PostMapping("/delete/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView deleteConfirm(@PathVariable String id) {

        this.drinkService.deleteById(id);

        return redirect("/drinks/all");
    }


    private LinkedList<DrinkAllViewModel> setDrinkAllViewModels(LinkedList<DrinkServiceModel> drinkServiceModels) {
        return drinkServiceModels.stream()
                .map(d -> {
                    DrinkAllViewModel drinkAllViewModel =
                            this.modelMapper.map(d, DrinkAllViewModel.class);
                    drinkAllViewModel.setIngredients(d.getIngredients());
                    return drinkAllViewModel;
                })
                .collect(Collectors.toCollection(LinkedList::new));
    }


    private void setIngredientsViewList(ModelAndView modelAndView) {
        Set<DrinkIngredientServiceModel> ingredientsList = this.drinkIngredientService.findAllDrinkIngredients();
        modelAndView.addObject(INGREDIENTS, ingredientsList);
    }


    private Set<DrinkIngredientServiceModel> getChosenIngredients(
            @RequestParam(value = "ingredients", required = false) List<String> ingredientsId) {
        Set<DrinkIngredientServiceModel> ingredients = new HashSet<>();
        for (String id : ingredientsId) {
            DrinkIngredientServiceModel ingredient = this.drinkIngredientService.findIngredientById(id);
            ingredients.add(ingredient);
        }
        return ingredients;
    }

    @ResponseBody
    @GetMapping("/menu/drinks")
    public List<DrinkAllViewModel> getDrinks() {
        List<DrinkServiceModel> drinkServiceModels = this.drinkService.findAllByOrderByName();
        List<DrinkAllViewModel> drinkAllViewModels = drinkServiceModels.stream()
                .map(d -> this.modelMapper.map(d, DrinkAllViewModel.class))
                .collect(Collectors.toList());

        return drinkAllViewModels;
    }

}
