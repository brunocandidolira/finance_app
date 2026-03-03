package com.finance.spring_app.Security;


import com.finance.spring_app.Service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
    @RequiredArgsConstructor
    public class SecurityConfig {

        private final JwtService jwtService;
        private  final JwtAuthenticationFilter jwtAuthFilter;
    private final org.springframework.web.cors.CorsConfigurationSource corsConfigurationSource;
        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
            http
                    .cors(Customizer.withDefaults())
                    .cors(cors->cors.configurationSource(corsConfigurationSource))
                    .csrf(csrf -> csrf.disable()) // desativa CSRF
                    .authorizeHttpRequests(auth -> auth
                            .requestMatchers("/auth/**").permitAll() // rotas públicas
                            .requestMatchers("/users/**").permitAll()
                            .anyRequest().authenticated() // resto precisa de login
                    )
                    .sessionManagement(session -> session
                            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                            ).addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);


            return http.build();
        }

        @Bean
        public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
            return config.getAuthenticationManager();
        }

    }