package com.pfe.smartwallet.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**") // Autorise tous les chemins (ex: /api/transactions)
                        .allowedOrigins("*") // Autorise toutes les sources (ton téléphone, Expo, etc.)
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Autorise toutes les actions
                        .allowedHeaders("*") // Autorise tous les headers
                        .allowCredentials(false);
            }
        };
    }
}