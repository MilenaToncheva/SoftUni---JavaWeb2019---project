package com.org.pizza.web.controller.moderator;

import com.org.pizza.domain.models.binding.IngredientAddBindingModel;
import com.org.pizza.domain.models.service.IngredientServiceModel;
import com.org.pizza.domain.models.view.IngredientViewModel;
import com.org.pizza.service.IngredientService;
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
import java.util.stream.Collectors;

import static com.org.pizza.constant.commonMessages.CommonMessages.BINDING_MODEL;

@Controller
@RequestMapping("/ingredients")
public class IngredientController extends BaseController {

    private final IngredientService ingredientService;
    private final ModelMapper modelMapper;

    @Autowired
    public IngredientController(IngredientService ingredientService, ModelMapper modelMapper) {
        this.ingredientService = ingredientService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/add")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public ModelAndView addIngredient(@ModelAttribute(name = BINDING_MODEL) IngredientAddBindingModel bindingModel,
                                      ModelAndView modelAndView) {

        modelAndView.addObject(BINDING_MODEL, bindingModel);
        return view("moderator/ingredient-add", modelAndView);
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public ModelAndView addIngredientConfirm(@Valid @ModelAttribute(name = BINDING_MODEL) IngredientAddBindingModel bindingModel,
                                             BindingResult bindingResult, ModelAndView modelAndView) {

        if (bindingResult.hasErrors()) {
            modelAndView.addObject(BINDING_MODEL, bindingModel);
            return view("moderator/ingredient-add", modelAndView);
        }

        IngredientServiceModel ingredientServiceModel = this.modelMapper.map(bindingModel, IngredientServiceModel.class);
        this.ingredientService.addNewIngredient(ingredientServiceModel);

        return redirect("/ingredients/all");
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public ModelAndView allCategories(ModelAndView modelAndView) {

        List<IngredientServiceModel> categoryServiceModels = this.ingredientService.findAllIngredients();
        List<IngredientViewModel> categoryViewModelList = categoryServiceModels
                .stream()
                .map(c -> this.modelMapper.map(c, IngredientViewModel.class))
                .collect(Collectors.toList());

        modelAndView.addObject(BINDING_MODEL, categoryViewModelList);


        return view("moderator/ingredient-all", modelAndView);
    }
//    @GetMapping("/edit/{id}")
//    @PreAuthorize("hasRole('ROLE_MODERATOR')")
//    public ModelAndView editIngredient(@PathVariable String id,
//                                       @Valid @ModelAttribute(name = BINDING_MODEL) IngredientAddBindingModel bindingModel,
//                                       ModelAndView modelAndView) {
//
//        IngredientServiceModel ingredientServiceModel = this.ingredientService.findIngredientById(id);
//        IngredientAddBindingModel ingredientEditBindingModel = this.modelMapper
//                .map(ingredientServiceModel, IngredientAddBindingModel.class);
//
//        modelAndView.addObject("modelId", id);
//        modelAndView.addObject("model", ingredientEditBindingModel);
//
//        modelAndView.addObject(BINDING_MODEL, bindingModel);
//
//        return view("moderator/ingredient-edit", modelAndView);
//    }
//
//    @PostMapping("/edit/{id}")
//    @PreAuthorize("hasRole('ROLE_MODERATOR')")
//    public ModelAndView editIngredientConfirm(@PathVariable String id,
//                                              @Valid @ModelAttribute(name = BINDING_MODEL) IngredientAddBindingModel bindingModel,
//                                              ModelAndView modelAndView, BindingResult bindingResult) {
//        if (bindingResult.hasErrors()) {
//            modelAndView.addObject(BINDING_MODEL, bindingModel);
//            return view("moderator/ingredient-edit", modelAndView);
//        }
//
//        IngredientAddBindingModel ingredientEditBindingModel = this.modelMapper
//                .map(bindingModel, IngredientAddBindingModel.class);
//
//        IngredientServiceModel ingredientServiceModel = this.modelMapper
//                .map(ingredientEditBindingModel, IngredientServiceModel.class);
//
//        this.ingredientService.editIngredient(ingredientServiceModel, id);
//
//        return redirect("moderator/ingredient-all");
//    }

}