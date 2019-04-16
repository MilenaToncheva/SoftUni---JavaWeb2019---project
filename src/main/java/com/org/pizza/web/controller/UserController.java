package com.org.pizza.web.controller;

import com.org.pizza.domain.models.binding.UserRegisterBindingModel;
import com.org.pizza.domain.models.service.UserServiceModel;
import com.org.pizza.domain.models.view.UserAllViewModel;
import com.org.pizza.service.UserService;
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
@RequestMapping("/users")
public class UserController extends BaseController {


    private final UserService userService;
    private final ModelMapper modelMapper;

    @Autowired
    public UserController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

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
                                        @Valid @ModelAttribute(name = BINDING_MODEL) UserRegisterBindingModel bindingModel,
                                        BindingResult bindingResult) {

        if (inputDataIsValid(modelAndView, bindingModel, bindingResult)) return view("register");

        UserServiceModel userServiceModel = this.modelMapper.map(bindingModel, UserServiceModel.class);
        this.userService.registerUser(userServiceModel);

        return redirect("/users/login");
    }


    @GetMapping("/login")
    @PreAuthorize("isAnonymous()")
    public ModelAndView login() {
        return view("login");
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView allUsers(ModelAndView modelAndView) {

        List<UserServiceModel> userServiceModels = this.userService.findAllUsers();
        List<UserAllViewModel> userAllViewModels = mapUserToUserAllViewModel(userServiceModels);
        modelAndView.addObject("model", userAllViewModels);

        return view("all-users", modelAndView);
    }

    @PostMapping("/set-user/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView setUser(@PathVariable String id) {

        this.userService.setUserRole(id, "user");
        return redirect("/users/all");
    }

    @PostMapping("/set-moderator/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView setModerator(@PathVariable String id) {
        this.userService.setUserRole(id, "moderator");
        return redirect("/users/all");
    }

    @PostMapping("/set-admin/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView setAdmin(@PathVariable String id) {
        this.userService.setUserRole(id, "admin");
        return redirect("/users/all");
    }

    @PostMapping("/delete/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView deleteUser(@PathVariable String id) {

        this.userService.deleteUser(id);

        return redirect("/users/all");
    }


    private boolean inputDataIsValid(ModelAndView modelAndView, @ModelAttribute(name = BINDING_MODEL) @Valid UserRegisterBindingModel bindingModel, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            modelAndView.addObject(BINDING_MODEL, bindingModel);
            return true;
        }

        if (!bindingModel.getPassword().equals(bindingModel.getConfirmPassword())) {
            return true;
        }
        return false;
    }

    private List<UserAllViewModel> mapUserToUserAllViewModel(List<UserServiceModel> userServiceModels) {
        return userServiceModels.stream()
                .map(u -> this.modelMapper.map(u, UserAllViewModel.class))
                .collect(Collectors.toList());
    }

}
