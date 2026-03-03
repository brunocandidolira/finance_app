package com.finance.spring_app.Config;



import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

    @Configuration
    public class CorsConfig {

        @Bean
        public CorsConfigurationSource corsConfigurationSource() {
            CorsConfiguration configuration = new CorsConfiguration();

            // Permite requisições do frontend
            configuration.setAllowedOrigins(List.of("http://localhost:3000"));

            // Métodos HTTP permitidos
            configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));

            // Headers permitidos
            configuration.setAllowedHeaders(List.of("*"));

            // Permite envio de credenciais (cookies, token, etc.)
            configuration.setAllowCredentials(true);

            // Aplica a configuração para todas as rotas
            UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
            source.registerCorsConfiguration("/**", configuration);

            return source;
        }
    }


