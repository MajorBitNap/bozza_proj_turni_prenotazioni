package com.majorbit.bozza_proj_turni_prenotazioni.application.dto;

public record StanzaDTO(
        Long id,
        String nome,
        int capienza,
        Long pianoId
)
{}
