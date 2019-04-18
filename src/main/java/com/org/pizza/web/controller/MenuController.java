package com.org.pizza.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MenuController extends BaseController {


    @GetMapping("/menu")
    public ModelAndView menu() {

        return view("menu");
    }
}
