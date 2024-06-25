package com.majorbit.bozza_proj_turni_prenotazioni.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostoDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String nome;
    private boolean disponibile;
    private Integer stanza;

}
