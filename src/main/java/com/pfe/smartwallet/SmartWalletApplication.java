package com.pfe.smartwallet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class SmartWalletApplication {

    public static void main(String[] args) {
        SpringApplication.run(SmartWalletApplication.class, args);
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                // Version Simplifiée "Sans Échec"
                registry.addMapping("/**")
                        .allowedOrigins("*")  // On autorise tout le monde
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        .allowedHeaders("*");
                // J'ai supprimé .allowCredentials(true) pour débloquer la situation
            }
        };
    }
}