package com.majorbit.bozza_proj_turni_prenotazioni.application.dto;

import com.majorbit.bozza_proj_turni_prenotazioni.domain.model.Posto;
import com.majorbit.bozza_proj_turni_prenotazioni.domain.model.Utente;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PrenotazioneDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private Date dataInizio;
    private Date dataFine;
    private String stato;
    private Posto posto;
    private Utente utente;

}