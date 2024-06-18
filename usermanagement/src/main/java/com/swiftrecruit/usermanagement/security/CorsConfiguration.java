package com.swiftrecruit.usermanagement.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.hibernate.annotations.DialectOverride.OverridesAnnotation;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

public class CorsConfiguration {

    /*
     * @Bean
     * public WebMvcConfigurer corsConfigurer() {
     * 
     * return new WebMvcConfigurer() {
     * 
     * @Override
     * public void addCorsMappings(CorsRegistry registry) {
     * registry.addMapping("/**")
     * .allowedOrigins("*")
     * .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
     * .allowedHeaders("*"); // Enable CORS for the whole application.
     * }
     * };
     * }
     */

}
