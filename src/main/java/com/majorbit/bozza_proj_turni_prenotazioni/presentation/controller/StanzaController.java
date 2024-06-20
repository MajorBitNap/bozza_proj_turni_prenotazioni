package com.majorbit.bozza_proj_turni_prenotazioni.presentation.controller;

import com.majorbit.bozza_proj_turni_prenotazioni.application.dto.StanzaDTO;
import com.majorbit.bozza_proj_turni_prenotazioni.application.service.GestioneStanza;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/v1/stanze")
public class StanzaController {

    private final GestioneStanza gestioneStanza;

    @Autowired
    public StanzaController(GestioneStanza gestioneStanza) {
        this.gestioneStanza = gestioneStanza;
    }

    @GetMapping("/{id}")
    public ResponseEntity<StanzaDTO> getStanzaById(@PathVariable Long id) {
        StanzaDTO stanza = gestioneStanza.getStanzaById(id);
        return ResponseEntity.ok(stanza);
    }

    @GetMapping
    public ResponseEntity<List<StanzaDTO>> getAllStanze() {
        List<StanzaDTO> stanze = gestioneStanza.getAllStanze();
        return ResponseEntity.ok(stanze);
    }

    @PostMapping
    public ResponseEntity<StanzaDTO> createStanza(@RequestBody StanzaDTO stanza) {
        StanzaDTO newStanza = gestioneStanza.createStanza(stanza);
        return ResponseEntity.status(HttpStatus.CREATED).body(newStanza);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StanzaDTO> updateStanza(@PathVariable Long id, @RequestBody StanzaDTO stanza) {
        StanzaDTO updatedStanza = gestioneStanza.updateStanza(id, stanza);
        return ResponseEntity.ok(updatedStanza);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStanza(@PathVariable Long id) {
        gestioneStanza.deleteStanza(id);
        return ResponseEntity.noContent().build();
    }


}


