package com.majorbit.bozza_proj_turni_prenotazioni.domain.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Utente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String cognome;

//  la password viene recuprerata tramite email dalla tabella login dov'è annotata come username
    @Column(nullable = false)
    private String email;

    @Column
    private String password;

//  nella richiesta assicurarsi che il ruolo sia in formato TUTTO MAIUSCOLO
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Ruolo ruolo;

}
