package com.majorbit.bozza_proj_turni_prenotazioni.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PianoDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String nome;
    private int numero;
    private Long sede;

}
