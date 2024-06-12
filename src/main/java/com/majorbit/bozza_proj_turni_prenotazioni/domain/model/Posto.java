package com.majorbit.bozza_proj_turni_prenotazioni.domain.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String nome;

    @Column
    private boolean disponibile;

    @ManyToOne
    @JoinColumn(name = "stanza_id")
    @JsonBackReference
    private Stanza stanza;

    @OneToMany(mappedBy = "posto", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Prenotazione> prenotazioni;

}
