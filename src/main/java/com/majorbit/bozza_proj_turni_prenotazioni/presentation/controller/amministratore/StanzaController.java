package com.majorbit.bozza_proj_turni_prenotazioni.presentation.controller.amministratore;

import com.majorbit.bozza_proj_turni_prenotazioni.application.dto.StanzaDTO;
import com.majorbit.bozza_proj_turni_prenotazioni.application.service.GestioneStanza;
import com.majorbit.bozza_proj_turni_prenotazioni.application.usecases.impl.ICheckCapienza;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@PreAuthorize("hasRole('AMMINISTRATORE')")
@RequestMapping("/api/v1/stanze")
public class StanzaController {

    private final GestioneStanza gestioneStanza;
    private final ICheckCapienza checkCapienza;

    @GetMapping("/{id}")
    public ResponseEntity<StanzaDTO> getStanzaById(@PathVariable Integer id) {
        var stanza = gestioneStanza.getStanzaById(id);
        return ResponseEntity.ok(stanza);
    }

    @GetMapping
    public ResponseEntity<List<StanzaDTO>> getAllStanze() {
        List<StanzaDTO> stanze = gestioneStanza.getAllStanze();
        return ResponseEntity.ok(stanze);
    }

    @PostMapping
    public ResponseEntity<StanzaDTO> createStanza(@RequestBody StanzaDTO stanza) {
        var newStanza = gestioneStanza.createStanza(stanza);
        return ResponseEntity.status(HttpStatus.CREATED).body(newStanza);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StanzaDTO> updateStanza(@PathVariable Integer id, @RequestBody StanzaDTO stanza) {
        var updatedStanza = gestioneStanza.updateStanza(id, stanza);
        return ResponseEntity.ok(updatedStanza);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStanza(@PathVariable Integer id) {
        gestioneStanza.deleteStanza(id);
        return ResponseEntity.noContent().build();
    }

}


