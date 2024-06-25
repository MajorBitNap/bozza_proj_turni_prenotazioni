package com.majorbit.bozza_proj_turni_prenotazioni.presentation.controller.amministratore;


import com.majorbit.bozza_proj_turni_prenotazioni.application.dto.PianoDTO;
import com.majorbit.bozza_proj_turni_prenotazioni.application.service.GestionePiano;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/piani")
@PreAuthorize("hasRole('AMMINISTRATORE')")
public class PianoController {

    private final GestionePiano gestionePiano;

    @GetMapping("/{id}")
    public ResponseEntity<PianoDTO> getPianoById(@PathVariable Integer id) {
        var piano = gestionePiano.getPianoById(id);
        return ResponseEntity.ok(piano);
    }
    @GetMapping
    public ResponseEntity<List<PianoDTO>> getAllPiani() {
        List<PianoDTO> piani = gestionePiano.getAllPiani();
        return ResponseEntity.ok(piani);
    }

    @PostMapping
    public ResponseEntity<PianoDTO> createPiano(@RequestBody PianoDTO piano) {
        var newPiano = gestionePiano.createPiano(piano);
        return ResponseEntity.status(HttpStatus.CREATED).body(newPiano);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PianoDTO> updatePiano(@PathVariable Integer id, @RequestBody PianoDTO piano) {
        var updatedPiano = gestionePiano.updatePiano(id, piano);
        return ResponseEntity.ok(updatedPiano);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePiano(@PathVariable Integer id) {
        gestionePiano.deletePiano(id);
        return ResponseEntity.noContent().build();
    }

}
