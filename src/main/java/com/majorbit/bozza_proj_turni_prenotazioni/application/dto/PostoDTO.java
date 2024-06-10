package com.majorbit.bozza_proj_turni_prenotazioni.application.dto;

public record PostoDTO(
    Long id,
    String nome,
    boolean disponibile,
    Long stanzaId
)
{}
