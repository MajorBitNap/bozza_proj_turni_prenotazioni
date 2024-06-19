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

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column
    private Date dataInizio;

    @Column
    private Date dataFine;

    @Column
    private String stato;


    @ManyToOne
    @JoinColumn(name = "posto_id")
    private Posto posto;

    @ManyToOne
    @JoinColumn(name = "utente_id")
    private Utente utente;


}
