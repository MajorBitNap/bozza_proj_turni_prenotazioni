package com.majorbit.bozza_proj_turni_prenotazioni.presentation.controller;

import com.majorbit.bozza_proj_turni_prenotazioni.application.dto.UtenteDTO;
import com.majorbit.bozza_proj_turni_prenotazioni.application.service.GestioneUtente;
import com.majorbit.bozza_proj_turni_prenotazioni.application.usecases.impl.IRegistraDipendente;
import com.majorbit.bozza_proj_turni_prenotazioni.application.usecases.impl.IRegistraModeratore;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/utenti")
public class UtenteController {

    private final GestioneUtente gestioneUtente;
    private final IRegistraDipendente registraDipendente;
    private final IRegistraModeratore registraModeratore;

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

    // registrazione da parte dell'amministratore di un utente con ruolo DIPENDENTE
    @PostMapping("/amministratore/registraDipendente")
    public ResponseEntity<Void> registraDipendente(@RequestBody UtenteDTO UtenteDTO) {
        registraDipendente.registraDipendente(UtenteDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    // registrazione da parte dell'amministratore di un utente con ruolo MODERATORE
    @PostMapping("/amministratore/registraModeratore")
    public ResponseEntity<Void> registraModeratore(@RequestBody UtenteDTO UtenteDTO) {
        registraModeratore.registraModeratore(UtenteDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
