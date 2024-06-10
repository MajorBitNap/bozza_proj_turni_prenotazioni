package com.majorbit.bozza_proj_turni_prenotazioni.domain.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
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

    @Column
    private Long postoId;

    @Column
    private Long utenteId;


}
