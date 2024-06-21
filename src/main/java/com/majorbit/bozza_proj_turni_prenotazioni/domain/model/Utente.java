package com.majorbit.bozza_proj_turni_prenotazioni.domain.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Utente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String cognome;

//  la password viene recuprerata tramite email dalla tabella login
    @Column(nullable = false)
    private String email;

//  nella richiesta assicurarsi che il ruolo sia in formato TUTTO MAIUSCOLO
    @Column(nullable = false)
    private Ruolo ruolo;

}
