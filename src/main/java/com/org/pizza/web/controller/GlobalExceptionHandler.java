package com.org.pizza.web.controller;

import com.org.pizza.validation.errors.*;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler extends BaseController {

    @ExceptionHandler({CategoryAlreadyExistException.class})
    public ModelAndView handlerCategoryAlreadyExistException(RuntimeException ex) {
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("message", ex.getMessage());
        return modelAndView;
    }

    @ExceptionHandler({DrinkAlreadyExistException.class})
    public ModelAndView handlerDrinkAlreadyExistException(RuntimeException ex) {
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("message", ex.getMessage());
        return modelAndView;
    }

    @ExceptionHandler({DrinkIngredientAlreadyExistException.class})
    public ModelAndView handlerDrinkIngredientAlreadyExistException(RuntimeException ex) {
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("message", ex.getMessage());
        return modelAndView;
    }

    @ExceptionHandler({IngredientAlreadyExistException.class})
    public ModelAndView handlerIngredientAlreadyExistException(RuntimeException ex) {
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("message", ex.getMessage());
        return modelAndView;
    }

    @ExceptionHandler({PizzaAlreadyExistException.class})
    public ModelAndView handlerPizzaAlreadyExistException(RuntimeException ex) {
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("message", ex.getMessage());
        return modelAndView;
    }

    @ExceptionHandler({UserAlreadyExistException.class})
    public ModelAndView handlerUserAlreadyExistException(RuntimeException ex) {
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("message", ex.getMessage());
        return modelAndView;
    }

    @ExceptionHandler({UserProfileEditException.class})
    public ModelAndView handlerUserProfileEditException(RuntimeException ex) {
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("message", ex.getMessage());
        return modelAndView;
    }

    @ExceptionHandler({UserRegistrationException.class})
    public ModelAndView handlerUserRegistrationException(RuntimeException ex) {
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("message", ex.getMessage());
        return modelAndView;
    }

    @ExceptionHandler({PizzaAddException.class})
    public ModelAndView handlerPizzaAddException(RuntimeException ex) {
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("message", ex.getMessage());
        return modelAndView;
    }

    @ExceptionHandler({IngredientAddException.class})
    public ModelAndView handlerIngredientAddException(RuntimeException ex) {
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("message", ex.getMessage());
        return modelAndView;
    }

    @ExceptionHandler({DrinkIngredientAddException.class})
    public ModelAndView handlerDrinkIngredientAddException(RuntimeException ex) {
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("message", ex.getMessage());
        return modelAndView;
    }

    @ExceptionHandler({DrinkAddException.class})
    public ModelAndView handlerDrinkAddException(RuntimeException ex) {
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("message", ex.getMessage());
        return modelAndView;
    }

    @ExceptionHandler({CategoryAddException.class})
    public ModelAndView handlerCategoryAddException(RuntimeException ex) {
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("message", ex.getMessage());
        return modelAndView;
    }

    @ExceptionHandler({CategoryNotFoundException.class})
    public ModelAndView handlerCategoryNotFound(RuntimeException ex) {
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("message", ex.getMessage());
        return modelAndView;
    }

    @ExceptionHandler({DrinkNotFoundException.class})
    public ModelAndView handlerDrinkNotFound(RuntimeException ex) {
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("message", ex.getMessage());
        return modelAndView;
    }

    @ExceptionHandler({DrinkIngredientNotFoundException.class})
    public ModelAndView handlerDrinkIngredientNotFound(RuntimeException ex) {
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("message", ex.getMessage());
        return modelAndView;
    }

    @ExceptionHandler({IngredientNotFoundException.class})
    public ModelAndView handlerIngredientNotFound(RuntimeException ex) {
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("message", ex.getMessage());
        return modelAndView;
    }

    @ExceptionHandler({PizzaNotFoundException.class})
    public ModelAndView handlerPizzaNotFound(RuntimeException ex) {
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("message", ex.getMessage());
        return modelAndView;
    }

    @ExceptionHandler({Throwable.class})
    public ModelAndView handleSqlException(Throwable ex) {
        ModelAndView modelAndView = new ModelAndView("error");

        Throwable throwable = ex;

        while (throwable.getCause() != null) {
            throwable = throwable.getCause();
        }

        modelAndView.addObject("message", throwable.getMessage());
        return modelAndView;
    }
}
