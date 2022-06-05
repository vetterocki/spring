package com.example.cinema.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class DefaultViewConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/")
                .setViewName("../templates/bootstrap/home");
        registry.addViewController("/login")
                .setViewName("../templates/bootstrap/login");
        registry.addViewController("/registration")
                .setViewName("../templates/bootstrap/register");
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
    }

}





