package com.org.pizza.web.controller.moderator;

import com.org.pizza.domain.models.binding.DrinkIngredientAddBindingModel;
import com.org.pizza.domain.models.service.DrinkIngredientServiceModel;
import com.org.pizza.domain.models.view.DrinkIngredientViewModel;
import com.org.pizza.service.DrinkIngredientService;
import com.org.pizza.web.controller.BaseController;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.org.pizza.constant.commonMessages.CommonMessages.BINDING_MODEL;

@Controller
@RequestMapping("/drink-ingredients")
public class DrinkIngredientController extends BaseController {

    private final DrinkIngredientService drinkIngredientService;
    private final ModelMapper modelMapper;

    @Autowired
    public DrinkIngredientController(DrinkIngredientService drinkIngredientService, ModelMapper modelMapper) {
        this.drinkIngredientService = drinkIngredientService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/add")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public ModelAndView addIngredient(@ModelAttribute(name = BINDING_MODEL) DrinkIngredientAddBindingModel bindingModel,
                                      ModelAndView modelAndView) {

        modelAndView.addObject(BINDING_MODEL, bindingModel);
        return view("moderator/drink-ingredient-add", modelAndView);
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public ModelAndView addIngredientConfirm(
            @Valid @ModelAttribute(name = BINDING_MODEL) DrinkIngredientAddBindingModel bindingModel,
            BindingResult bindingResult, ModelAndView modelAndView) {

        if (bindingResult.hasErrors()) {
            modelAndView.addObject(BINDING_MODEL, bindingModel);
            return view("moderator/drink-ingredient-add", modelAndView);
        }

        DrinkIngredientServiceModel drinkIngredientServiceModel = this.modelMapper.map(
                bindingModel, DrinkIngredientServiceModel.class);
        this.drinkIngredientService.addNewIngredient(drinkIngredientServiceModel);

        return redirect("/drink-ingredients/all");
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public ModelAndView allDrinkIngredients(ModelAndView modelAndView) {

        Set<DrinkIngredientServiceModel> drinkIngredientServiceModels = this.drinkIngredientService.findAllDrinkIngredients();
        Set<DrinkIngredientViewModel> drinkIngredientViewModels = drinkIngredientServiceModels
                .stream()
                .map(c -> this.modelMapper.map(c, DrinkIngredientViewModel.class))
                .collect(Collectors.toSet());

        modelAndView.addObject(BINDING_MODEL, drinkIngredientViewModels);


        return view("moderator/drink-ingredient-all", modelAndView);
    }

    @GetMapping("/edit/{id}")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public ModelAndView editIngredient(@PathVariable String id,
                                       @ModelAttribute(name = BINDING_MODEL) DrinkIngredientAddBindingModel bindingModel,
                                       ModelAndView modelAndView) {

        DrinkIngredientServiceModel drinkIngredientServiceModel = this.drinkIngredientService.findIngredientById(id);
        DrinkIngredientAddBindingModel drinkIngredientAddBindingModel = this.modelMapper
                .map(drinkIngredientServiceModel, DrinkIngredientAddBindingModel.class);

        modelAndView.addObject("modelId", id);
        modelAndView.addObject(BINDING_MODEL, drinkIngredientAddBindingModel);

        return view("moderator/drink-ingredient-edit", modelAndView);
    }

    @PostMapping("/edit/{id}")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public ModelAndView editIngredientConfirm(@PathVariable String id,
                                              @Valid @ModelAttribute(name = BINDING_MODEL)
                                                      DrinkIngredientAddBindingModel bindingModel,
                                              BindingResult bindingResult, ModelAndView modelAndView) {
        if (bindingResult.hasErrors()) {
            modelAndView.addObject(BINDING_MODEL, bindingModel);
            return redirect("moderator/drink-ingredient-edit");
        }

        DrinkIngredientAddBindingModel drinkIngredientAddBindingModel = this.modelMapper
                .map(bindingModel, DrinkIngredientAddBindingModel.class);

        DrinkIngredientServiceModel drinkIngredientServiceModel = this.modelMapper
                .map(drinkIngredientAddBindingModel, DrinkIngredientServiceModel.class);
        drinkIngredientServiceModel.setId(id);
        this.drinkIngredientService.editIngredient(drinkIngredientServiceModel);

        return redirect("/drink-ingredients/all");
    }

    @PostMapping("/delete/{id}")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public ModelAndView deleteCategory(@PathVariable String id) {
        this.drinkIngredientService.deleteById(id);
        return redirect("/drink-ingredients/all");
    }
}