package com.org.pizza.web.controller;

import org.springframework.web.servlet.ModelAndView;

public abstract class BaseController {

    ModelAndView view(String viewName, ModelAndView modelAndView) {
        modelAndView.setViewName(viewName);
        return modelAndView;
    }

    ModelAndView view(String viewName) {
        return this.view(viewName, new ModelAndView());
    }

    ModelAndView redirect(String url) {
        return this.view("redirect:" + url);
    }
}
