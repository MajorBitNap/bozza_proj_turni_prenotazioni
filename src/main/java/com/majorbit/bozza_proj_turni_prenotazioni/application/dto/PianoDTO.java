package com.majorbit.bozza_proj_turni_prenotazioni.application.dto;

public record PianoDTO(
        Long id,
        String nome,
        int numero,
        Long sedeId
)
{}
