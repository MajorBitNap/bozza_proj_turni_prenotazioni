package com.majorbit.bozza_proj_turni_prenotazioni.presentation.controller;


import com.majorbit.bozza_proj_turni_prenotazioni.application.dto.PianoDTO;
import com.majorbit.bozza_proj_turni_prenotazioni.application.usecases.spec.GestionePiano;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/piani")
public class PianoController {

    private final GestionePiano gestionePiano;

    @Autowired
    public PianoController(GestionePiano gestionePiano) {
        this.gestionePiano = gestionePiano;
    }

    @GetMapping("/{id}")
    public ResponseEntity<PianoDTO> getPianoById(@PathVariable Long id) {
        PianoDTO piano = gestionePiano.getPianoById(id);
        return ResponseEntity.ok(piano);
    }

    @PostMapping
    public ResponseEntity<PianoDTO> createPiano(@RequestBody PianoDTO piano) {
        PianoDTO newPiano = gestionePiano.createPiano(piano);
        return ResponseEntity.status(HttpStatus.CREATED).body(newPiano);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PianoDTO> updatePiano(@PathVariable Long id, @RequestBody PianoDTO piano) {
        PianoDTO updatedPiano = gestionePiano.updatePiano(id, piano);
        return ResponseEntity.ok(updatedPiano);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePiano(@PathVariable Long id) {
        gestionePiano.deletePiano(id);
        return ResponseEntity.noContent().build();
    }

}
