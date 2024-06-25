package com.majorbit.bozza_proj_turni_prenotazioni.presentation.controller.amministratore;

import com.majorbit.bozza_proj_turni_prenotazioni.application.dto.UtenteDTO;
import com.majorbit.bozza_proj_turni_prenotazioni.application.service.GestioneUtente;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@PreAuthorize("hasRole('AMMINISTRATORE')")
@RequestMapping("/api/v1/utenti")
public class UtenteController {

    private final GestioneUtente gestioneUtente;

    @GetMapping("/{id}")
    public ResponseEntity<UtenteDTO> getUtenteById(@PathVariable Integer id) {
        var utente = gestioneUtente.getUtenteById(id);
        return ResponseEntity.ok(utente);
    }

    @GetMapping
    public ResponseEntity<List<UtenteDTO>> getAllUtenti() {
        List<UtenteDTO> utenti = gestioneUtente.getAllUtenti();
        return ResponseEntity.ok(utenti);
    }

    @PostMapping
    public ResponseEntity<UtenteDTO> createUtente(@RequestBody UtenteDTO utente) {
        var newUtente = gestioneUtente.createUtente(utente);
        return ResponseEntity.status(HttpStatus.CREATED).body(newUtente);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UtenteDTO> updateUtente(@PathVariable Integer id, @RequestBody UtenteDTO utente) {
        var updatedUtente = gestioneUtente.updateUtente(id, utente);
        return ResponseEntity.ok(updatedUtente);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUtente(@PathVariable Integer id) {
        gestioneUtente.deleteUtente(id);
        return ResponseEntity.noContent().build();
    }

}
