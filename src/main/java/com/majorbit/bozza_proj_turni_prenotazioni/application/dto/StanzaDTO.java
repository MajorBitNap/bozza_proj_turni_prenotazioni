package com.majorbit.bozza_proj_turni_prenotazioni.application.dto;

import com.majorbit.bozza_proj_turni_prenotazioni.domain.model.Piano;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StanzaDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String nome;
    private int capienza;
    private Piano piano;

}
