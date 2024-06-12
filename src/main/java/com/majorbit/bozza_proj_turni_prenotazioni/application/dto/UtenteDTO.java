package com.majorbit.bozza_proj_turni_prenotazioni.application.dto;

import com.majorbit.bozza_proj_turni_prenotazioni.domain.model.Ruolo;

public record UtenteDTO(
        Long id,
        String nome,
        String cognome,
        String email,
        Ruolo ruolo)
{}
