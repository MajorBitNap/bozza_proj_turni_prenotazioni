package com.majorbit.bozza_proj_turni_prenotazioni.application.mapper;

import com.majorbit.bozza_proj_turni_prenotazioni.application.dto.UtenteDTO;
import com.majorbit.bozza_proj_turni_prenotazioni.domain.model.Utente;
import org.springframework.stereotype.Component;

@Component
public class UtenteMapper {

    public UtenteDTO toDTO(Utente utente) {
        return new UtenteDTO(
                utente.getNome(),
                utente.getCognome(),
                utente.getEmail(),
                utente.getPassword(),
                utente.getRuolo()
        );
    }

    public Utente toEntity(UtenteDTO utenteDTO) {
        var utente = new Utente();
        utente.setNome(utenteDTO.getNome());
        utente.setCognome(utenteDTO.getCognome());
        utente.setEmail(utenteDTO.getEmail());
        utente.setRuolo(utenteDTO.getRuolo());
        return utente;
    }
}
