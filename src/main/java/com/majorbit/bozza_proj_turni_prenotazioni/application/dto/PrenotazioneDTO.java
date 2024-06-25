package com.majorbit.bozza_proj_turni_prenotazioni.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.sql.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PrenotazioneDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Date dataInizio;
    private Date dataFine;
    private String stato;
    private Integer posto;
    private Integer utente;

}
