package com.majorbit.bozza_proj_turni_prenotazioni.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Sede {

    @Id
    private int id;

    @Column
    private String nome;

    @Column
    private String indirizzo;

    private List<Piano> piani;

}
