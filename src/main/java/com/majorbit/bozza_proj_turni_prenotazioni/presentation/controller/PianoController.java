package com.majorbit.bozza_proj_turni_prenotazioni.presentation.controller;


import com.majorbit.bozza_proj_turni_prenotazioni.application.dto.PianoDTO;
import com.majorbit.bozza_proj_turni_prenotazioni.application.dto.StanzaDTO;
import com.majorbit.bozza_proj_turni_prenotazioni.application.service.spec.PianoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/piani")
public class PianoController {

    private final PianoService pianoService;

    @Autowired
    public PianoController(PianoService pianoService) {
        this.pianoService = pianoService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<PianoDTO> getPianoById(@PathVariable Long id) {
        PianoDTO piano = pianoService.getPianoById(id);
        return ResponseEntity.ok(piano);
    }

    @PostMapping
    public ResponseEntity<PianoDTO> createPiano(@RequestBody PianoDTO piano) {
        PianoDTO newPiano = pianoService.createPiano(piano);
        return ResponseEntity.status(HttpStatus.CREATED).body(newPiano);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PianoDTO> updatePiano(@PathVariable Long id, @RequestBody PianoDTO piano) {
        PianoDTO updatedPiano = pianoService.updatePiano(id, piano);
        return ResponseEntity.ok(updatedPiano);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePiano(@PathVariable Long id) {
        pianoService.deletePiano(id);
        return ResponseEntity.noContent().build();
    }

}
