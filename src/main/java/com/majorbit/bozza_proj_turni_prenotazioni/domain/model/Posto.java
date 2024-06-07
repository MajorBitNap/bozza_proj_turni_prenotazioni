package com.majorbit.bozza_proj_turni_prenotazioni.domain.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import org.springframework.data.annotation.Id;


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

    public Posto() {
    }

    public Posto(int id, String nome, boolean disponibile, int stanzaId) {
        this.id = id;
        this.nome = nome;
        this.disponibile = disponibile;
        this.stanzaId = stanzaId;
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

    public boolean isDisponibile() {
        return disponibile;
    }

    public void setDisponibile(boolean disponibile) {
        this.disponibile = disponibile;
    }

    public int getStanzaId() {
        return stanzaId;
    }

    public void setStanzaId(int stanzaId) {
        this.stanzaId = stanzaId;
    }

}
