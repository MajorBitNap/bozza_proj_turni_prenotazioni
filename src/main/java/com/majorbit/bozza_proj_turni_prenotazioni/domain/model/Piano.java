package com.majorbit.bozza_proj_turni_prenotazioni.domain.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Piano {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String nome;

    @Column
    private int numero;

    @ManyToOne
    @JoinColumn(name = "sede_id")
    private Sede sede;

    //forse da introdure JSON Ignore
    @OneToMany(mappedBy = "piano", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Stanza> stanze;

}
