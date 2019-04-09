package com.org.pizza.web.controller;

import com.org.pizza.domain.models.binding.UserRegisterBindingModel;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class UserController extends BaseController {

    private static final String BINDING_MODEL = "bindingModel";

    @GetMapping("/register")
    @PreAuthorize("isAnonymous()")
    public ModelAndView register(ModelAndView modelAndView,
                                 @ModelAttribute(name = BINDING_MODEL) UserRegisterBindingModel bindingModel) {
        modelAndView.addObject(BINDING_MODEL, bindingModel);
        return view("register", modelAndView);
    }

    @PostMapping("/register")
    @PreAuthorize("isAnonymous()")
    public ModelAndView registerConfirm(ModelAndView modelAndView,
                                        @Valid @ModelAttribute(name = BINDING_MODEL) UserRegisterBindingModel bindingModel) {

        if (!bindingModel.getPassword().equals(bindingModel.getConfirmPassword())) {
            return super.view("register");
        }
        modelAndView.addObject(BINDING_MODEL, bindingModel);
//        this.userService.registerUser(this.modelMapper.map(bindingModel, UserServiceModel.class));
//        TODO:
        return redirect("/index");
    }
}
