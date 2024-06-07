package com.majorbit.bozza_proj_turni_prenotazioni.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.util.List;

@Entity
public class Stanza {

    @Id
    @GeneratedValue
    private int id;

    @Column
    private String nome;

    @Column
    private int capienza;

    @Column(name = "piano_id")
    private int pianoId;

    private List<Posto> posti;

    public Stanza() {
    }

    public Stanza(int id, String nome, int capienza, int pianoId) {
        this.id = id;
        this.nome = nome;
        this.capienza = capienza;
        this.pianoId = pianoId;
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

    public int getCapienza() {
        return capienza;
    }

    public void setCapienza(int capienza) {
        this.capienza = capienza;
    }

    public List<Posto> getPosti() {
        return posti;
    }

    public void setPosti(List<Posto> posti) {
        this.posti = posti;
    }

    public int getPianoId() {
        return pianoId;
    }

    public void setPianoId(int pianoId) {
        this.pianoId = pianoId;
    }

}
