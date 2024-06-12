package com.majorbit.bozza_proj_turni_prenotazioni.application.dto;

import com.majorbit.bozza_proj_turni_prenotazioni.domain.model.Stanza;

public record PostoDTO(
    Long id,
    String nome,
    boolean disponibile,
    Stanza stanza
)
{}
