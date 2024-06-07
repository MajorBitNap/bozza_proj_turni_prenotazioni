package com.majorbit.bozza_proj_turni_prenotazioni.domain.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Posto {
    @Id
    @GeneratedValue
    private int id;

    @Column
    private String nome;

    @Column
    private boolean disponibile;


    @Column(name = "stanza_id")
    private int stanzaId;

}
