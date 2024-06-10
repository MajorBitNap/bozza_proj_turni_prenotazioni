package com.majorbit.bozza_proj_turni_prenotazioni.domain.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Login {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_utente", nullable = false)
    private Long utente;

    @Column(name = "password_utente", nullable = false)
    private String passwordUtente;

}

