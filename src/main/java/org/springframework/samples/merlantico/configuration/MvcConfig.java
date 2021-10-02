package org.springframework.samples.merlantico.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry){
        registry.addViewController("/login").setViewName("users/login");
        registry.addViewController("/login-error").setViewName("users/login-error");
        registry.addViewController("/logout").setViewName("users/logout");
    }

}
