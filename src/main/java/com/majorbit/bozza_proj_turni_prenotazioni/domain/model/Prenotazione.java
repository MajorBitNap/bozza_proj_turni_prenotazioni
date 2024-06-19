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
    private Long id;

    @Column(name = "data_inizio")
    private Date dataInizio;

    @Column(name = "data_fine")
    private Date dataFine;

    @Column
    private String stato;


    @Column(name = "posto_id")
    private Long posto;

    @Column(name = "utente_id")
    private Long utente;


}
