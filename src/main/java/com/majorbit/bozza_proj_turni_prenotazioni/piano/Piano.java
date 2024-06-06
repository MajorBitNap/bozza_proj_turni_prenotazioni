package com.majorbit.bozza_proj_turni_prenotazioni.piano;

import com.majorbit.bozza_proj_turni_prenotazioni.stanza.Stanza;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.util.List;

@Entity
public class Piano {

    @Id
    @GeneratedValue
    private int id;//asdadas

    @Column
    private String nome;

    @Column
    private int numero;

    @Column(name = "sede_id")
    private int sedeId;

    private List<Stanza> stanze;

    public Piano() {
    }

    public Piano(int id, String nome, int numero, int sedeId) {
        this.id = id;
        this.nome = nome;
        this.numero = numero;
        this.sedeId = sedeId;
    }

    public List<Stanza> getStanze() {
        return stanze;
    }

    public void setStanze(List<Stanza> stanze) {
        this.stanze = stanze;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getSedeId() {
        return sedeId;
    }

    public void setSedeId(int sedeId) {
        this.sedeId = sedeId;
    }
}
