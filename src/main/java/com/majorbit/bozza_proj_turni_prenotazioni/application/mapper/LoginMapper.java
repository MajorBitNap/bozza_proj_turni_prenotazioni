package com.majorbit.bozza_proj_turni_prenotazioni.application.mapper;

import com.majorbit.bozza_proj_turni_prenotazioni.application.dto.LoginDTO;
import com.majorbit.bozza_proj_turni_prenotazioni.domain.model.Utente;
import com.majorbit.bozza_proj_turni_prenotazioni.domain.repository.UtenteRepository;
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
        var utente = utenteRepository.findByEmail(loginDTO.getUsername());
        if (!utente.isPresent()) {}
        return utente.get();
    }
}
