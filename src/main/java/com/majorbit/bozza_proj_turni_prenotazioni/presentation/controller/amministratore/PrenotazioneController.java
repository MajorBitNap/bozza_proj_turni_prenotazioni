package com.majorbit.bozza_proj_turni_prenotazioni.presentation.controller.amministratore;

import com.majorbit.bozza_proj_turni_prenotazioni.application.dto.PrenotazioneDTO;
import com.majorbit.bozza_proj_turni_prenotazioni.application.service.GestionePrenotazione;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@PreAuthorize("hasRole('AMMINISTRATORE')")
@RequestMapping("/api/v1/prenotazioni")
public class PrenotazioneController {

    private final GestionePrenotazione gestionePrenotazione;

    @GetMapping("/{id}")
    public ResponseEntity<PrenotazioneDTO> getPrenotazioneById(@PathVariable Integer id) {
        var prenotazione = gestionePrenotazione.getPrenotazioneById(id);
        return ResponseEntity.ok(prenotazione);
    }

    @GetMapping
    public ResponseEntity<List<PrenotazioneDTO>> getAllPrenotazioni() {
        List<PrenotazioneDTO> prenotazioni = gestionePrenotazione.getAllPrenotazioni();
        return ResponseEntity.ok(prenotazioni);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PrenotazioneDTO> updatePrenotazione(@PathVariable Integer id, @RequestBody PrenotazioneDTO prenotazione) {
        var updatedPrenotazione = gestionePrenotazione.updatePrenotazione(id, prenotazione);
        return ResponseEntity.ok(updatedPrenotazione);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePrenotazione(@PathVariable Integer id) {
        gestionePrenotazione.deletePrenotazione(id);
        return ResponseEntity.noContent().build();
    }

}

