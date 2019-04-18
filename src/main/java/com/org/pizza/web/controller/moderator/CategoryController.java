package com.org.pizza.web.controller.moderator;

import com.org.pizza.domain.models.binding.CategoryAddBindingModel;
import com.org.pizza.domain.models.service.CategoryServiceModel;
import com.org.pizza.domain.models.view.CategoryViewModel;
import com.org.pizza.service.CategoryService;
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
import java.util.stream.Collectors;

import static com.org.pizza.constant.commonMessages.CommonMessages.BINDING_MODEL;

@Controller
@RequestMapping("/categories")
public class CategoryController extends BaseController {

    private final CategoryService categoryService;
    private final ModelMapper modelMapper;

    @Autowired
    public CategoryController(CategoryService categoryService, ModelMapper modelMapper) {
        this.categoryService = categoryService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/add")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public ModelAndView addCategory(ModelAndView modelAndView,
                                    @ModelAttribute(name = BINDING_MODEL) CategoryAddBindingModel bindingModel) {
        modelAndView.addObject(BINDING_MODEL, bindingModel);
        return view("moderator/category-add", modelAndView);
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public ModelAndView addCategoryConfirm(
            @Valid @ModelAttribute(name = BINDING_MODEL) CategoryAddBindingModel bindingModel,
            BindingResult bindingResult, ModelAndView modelAndView) {

        if (bindingResult.hasErrors()) {
            modelAndView.addObject(BINDING_MODEL, bindingModel);
            return view("moderator/category-add", modelAndView);
        }

        CategoryServiceModel categoryServiceModel = this.modelMapper.map(bindingModel, CategoryServiceModel.class);

        this.categoryService.addNewCategory(categoryServiceModel);
        return redirect("/categories/all");
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public ModelAndView allCategories(ModelAndView modelAndView) {

        List<CategoryServiceModel> categoryServiceModels = this.categoryService.findAllCategories();
        List<CategoryViewModel> categoryViewModelList = categoryServiceModels
                .stream()
                .map(c -> this.modelMapper.map(c, CategoryViewModel.class))
                .collect(Collectors.toList());

        modelAndView.addObject(BINDING_MODEL, categoryViewModelList);


        return view("moderator/category-all", modelAndView);
    }

    @PostMapping("/delete/{id}")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public ModelAndView deleteCategory(@PathVariable String id) {

        this.categoryService.deleteById(id);

        return redirect("/categories/all");
    }
}
