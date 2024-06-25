package com.majorbit.bozza_proj_turni_prenotazioni.application.dto;

import com.majorbit.bozza_proj_turni_prenotazioni.domain.model.Ruolo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UtenteDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String nome;
    private String cognome;
    private String email;
    private String password;
    private Ruolo ruolo;

}
