package com.org.pizza.web.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class OurTeamController extends BaseController {

    @GetMapping("/our-team")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView ourTeam() {
        return view("our-team");
    }
}
