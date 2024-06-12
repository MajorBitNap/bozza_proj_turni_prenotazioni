package com.majorbit.bozza_proj_turni_prenotazioni.domain.model;


import jakarta.persistence.*;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Posto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String nome;

    @Column
    private boolean disponibile;


    @Column(name = "stanza_id")
    private Long stanzaId;

}
