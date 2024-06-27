package com.majorbit.bozza_proj_turni_prenotazioni.application.mapper;

import com.majorbit.bozza_proj_turni_prenotazioni.application.dto.LoginDTO;
import com.majorbit.bozza_proj_turni_prenotazioni.domain.model.Utente;
import com.majorbit.bozza_proj_turni_prenotazioni.domain.repository.UtenteRepository;
import com.majorbit.bozza_proj_turni_prenotazioni.util.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LoginMapper {

    private final UtenteRepository utenteRepository;

    public LoginDTO toDTO(Utente utente) {
        return LoginDTO.builder()
                        .username(utente.getEmail())
                        .password(utente.getPassword())
                        .build();
    }

    public Utente toEntity(LoginDTO loginDTO) {
        return utenteRepository.findByEmail(loginDTO.getUsername()).orElseThrow(() -> new ResourceNotFoundException("Resource not found"));
    }
}
