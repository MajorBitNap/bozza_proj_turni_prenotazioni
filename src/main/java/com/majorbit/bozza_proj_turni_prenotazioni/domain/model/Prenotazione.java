package com.majorbit.bozza_proj_turni_prenotazioni.domain.model;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Prenotazione {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "data_inizio", nullable = false)
    private Date dataInizio;

    @Column(name = "data_fine", nullable = false)
    private Date dataFine;

//  cambia da string a enum
    @Column
    private String stato;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "posto_id")
    private Posto posto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "utente_id")
    private Utente utente;

}
