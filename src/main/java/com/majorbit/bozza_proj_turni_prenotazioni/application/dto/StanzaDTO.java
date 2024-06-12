package com.majorbit.bozza_proj_turni_prenotazioni.application.dto;

import com.majorbit.bozza_proj_turni_prenotazioni.domain.model.Piano;

public record StanzaDTO(
        Long id,
        String nome,
        int capienza,
        Piano piano
)
{}
