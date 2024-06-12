package com.majorbit.bozza_proj_turni_prenotazioni.application.dto;

import com.majorbit.bozza_proj_turni_prenotazioni.domain.model.Sede;

public record PianoDTO(
        Long id,
        String nome,
        int numero,
        Sede sede
)
{}
