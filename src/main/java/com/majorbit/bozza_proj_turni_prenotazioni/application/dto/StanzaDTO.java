package com.majorbit.bozza_proj_turni_prenotazioni.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StanzaDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String nome;
    private int capienza;
    private Integer piano;
    private List<Integer> posti;

}
