package com.example.PokeApi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Clase Aplication 
 * @author Manuel Mateos de Torres
 * 
 */

@SpringBootApplication
public class PokeApiApplication {

	/**
	 * Método main desde el que se lanza la SpringApplication
	 */
    public static void main(String[] args) {
        SpringApplication.run(PokeApiApplication.class, args);
    }

    /**
     * Configuración para evitar el bloqueo CORS
     */
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/api/**")
                        .allowedOrigins("http://127.0.0.1:5500") // Reemplaza con el origen correcto de tu página web
                        .allowedMethods("GET", "POST", "DELETE")
                        .allowedHeaders("*");
            }
        };
    }
}
