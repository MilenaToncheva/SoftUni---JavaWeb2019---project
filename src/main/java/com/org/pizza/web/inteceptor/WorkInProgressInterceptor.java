package com.org.pizza.web.inteceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class WorkInProgressInterceptor extends HandlerInterceptorAdapter {

    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {

        String workTime = "FROM -∞ TO ∞+";
        if (modelAndView == null) {
            modelAndView = new ModelAndView();
        }

        modelAndView.addObject("workTime", workTime);
    }
}
