package com.majorbit.bozza_proj_turni_prenotazioni.domain.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Stanza {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private int capienza;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "piano_id")
    private Piano piano;

    @OneToMany(mappedBy = "stanza", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Posto> posti;

}
