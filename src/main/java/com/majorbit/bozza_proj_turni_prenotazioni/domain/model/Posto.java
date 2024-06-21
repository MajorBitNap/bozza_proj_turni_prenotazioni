package com.majorbit.bozza_proj_turni_prenotazioni.domain.model;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Posto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private boolean disponibile;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stanza_id")
    private Stanza stanza;

}
