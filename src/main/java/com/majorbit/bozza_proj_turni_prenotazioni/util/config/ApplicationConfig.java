package com.majorbit.bozza_proj_turni_prenotazioni.util.config;

import com.majorbit.bozza_proj_turni_prenotazioni.domain.model.Utente;
import com.majorbit.bozza_proj_turni_prenotazioni.domain.repository.LoginRepository;
import com.majorbit.bozza_proj_turni_prenotazioni.domain.repository.UtenteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {

    private final UtenteRepository utenteRepository;

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> utenteRepository.findByEmail(username)
                .orElseThrow(
                        () -> new UsernameNotFoundException("User not found!")
                );
    }
}
