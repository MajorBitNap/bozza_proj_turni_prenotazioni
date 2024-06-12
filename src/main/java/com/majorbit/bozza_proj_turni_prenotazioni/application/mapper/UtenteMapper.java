package com.majorbit.bozza_proj_turni_prenotazioni.application.mapper;

import com.majorbit.bozza_proj_turni_prenotazioni.application.dto.UtenteDTO;
import com.majorbit.bozza_proj_turni_prenotazioni.domain.model.Utente;
import org.springframework.stereotype.Component;

@Component
public class UtenteMapper {

    public static UtenteDTO toDTO(Utente utente) {
        return new UtenteDTO(
                utente.getId(),
                utente.getNome(),
                utente.getCognome(),
                utente.getEmail(),
                utente.getRuolo()
        );
    }

    public static Utente toEntity(UtenteDTO utenteDTO) {
        Utente utente = new Utente();
        utente.setId(utenteDTO.id());
        utente.setNome(utenteDTO.nome());
        utente.setCognome(utenteDTO.cognome());
        utente.setEmail(utenteDTO.email());
        utente.setRuolo(utenteDTO.ruolo());
        return utente;
    }
}
