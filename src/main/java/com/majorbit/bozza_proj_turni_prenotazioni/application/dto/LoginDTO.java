package com.majorbit.bozza_proj_turni_prenotazioni.application.dto;

import com.majorbit.bozza_proj_turni_prenotazioni.domain.model.Utente;
//implementa serializable
public record LoginDTO(
        Long id,
        Utente utente,
        String utentePassword
) {
}
