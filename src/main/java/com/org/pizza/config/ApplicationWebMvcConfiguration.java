package com.org.pizza.config;

import com.org.pizza.web.inteceptor.TitleInterceptor;
import com.org.pizza.web.inteceptor.WorkInProgressInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ApplicationWebMvcConfiguration implements WebMvcConfigurer {
    private final TitleInterceptor titleInterceptor;
    private final WorkInProgressInterceptor workInProgressInterceptor;

    @Autowired
    public ApplicationWebMvcConfiguration(TitleInterceptor titleInterceptor, WorkInProgressInterceptor workInProgressInterceptor) {
        this.titleInterceptor = titleInterceptor;
        this.workInProgressInterceptor = workInProgressInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(this.workInProgressInterceptor);
        registry.addInterceptor(this.titleInterceptor);
    }
}

