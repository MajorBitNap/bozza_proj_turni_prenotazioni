package com.majorbit.bozza_proj_turni_prenotazioni.application.mapper;

import com.majorbit.bozza_proj_turni_prenotazioni.application.dto.UtenteDTO;
import com.majorbit.bozza_proj_turni_prenotazioni.domain.model.Utente;
import org.springframework.stereotype.Component;

@Component
public class UtenteMapper {

    public UtenteDTO toDTO(Utente utente) {
        return UtenteDTO.builder()
                .nome(utente.getNome())
                .cognome(utente.getCognome())
                .email(utente.getEmail())
                .password(utente.getPassword())
                .ruolo(utente.getRuolo())
                .build();
    }

    public Utente toEntity(UtenteDTO utenteDTO) {
        return Utente.builder()
                .nome(utenteDTO.getNome())
                .cognome(utenteDTO.getCognome())
                .email(utenteDTO.getEmail())
                .ruolo(utenteDTO.getRuolo())
                .build();
    }
}
