package com.majorbit.bozza_proj_turni_prenotazioni.persistance.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.List;

@Entity
public class Sede {

    @Id
    private int id;

    @Column
    private String nome;

    @Column
    private String indirizzo;

    private List<Piano> piani;

    public Sede() {
    }

    public Sede(int id, String nome, String indirizzo) {
        this.id = id;
        this.nome = nome;
        this.indirizzo = indirizzo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public List<Piano> getPiani() {
        return piani;
    }
}
