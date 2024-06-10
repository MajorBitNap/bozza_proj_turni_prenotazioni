package com.majorbit.bozza_proj_turni_prenotazioni.application.dto;

import java.sql.Date;

public record PrenotazioneDTO(
        Long id,
        Date dataInizio,
        Date dataFine,
        String stato,
        Long postoId,
        Long utenteId
){}
