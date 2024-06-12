package com.majorbit.bozza_proj_turni_prenotazioni.application.dto;

import com.majorbit.bozza_proj_turni_prenotazioni.domain.model.Posto;
import com.majorbit.bozza_proj_turni_prenotazioni.domain.model.Utente;

import java.sql.Date;

public record PrenotazioneDTO(
        Long id,
        Date dataInizio,
        Date dataFine,
        String stato,
        Posto posto,
        Utente utente
){}
