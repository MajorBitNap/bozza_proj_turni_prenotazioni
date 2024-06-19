package com.majorbit.bozza_proj_turni_prenotazioni.util.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests(authorize -> authorize
                        .anyRequest().permitAll()  // Permette tutte le richieste senza autenticazione
                )
                .csrf(csrf -> csrf.disable());  // Disabilita CSRF

        return http.build();
    }

}


