package com.majorbit.bozza_proj_turni_prenotazioni.domain.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Stanza {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String nome;

    @Column
    private int capienza;

    @Column(name = "piano_id")
    private Long pianoId;

    private List<Posto> posti;

}
