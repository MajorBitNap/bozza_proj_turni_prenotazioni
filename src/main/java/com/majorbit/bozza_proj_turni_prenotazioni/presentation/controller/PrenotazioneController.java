package com.majorbit.bozza_proj_turni_prenotazioni.presentation.controller;
import com.majorbit.bozza_proj_turni_prenotazioni.application.dto.PrenotazioneDTO;

import com.majorbit.bozza_proj_turni_prenotazioni.application.dto.StanzaDTO;
import com.majorbit.bozza_proj_turni_prenotazioni.application.service.GestionePrenotazione;
import com.majorbit.bozza_proj_turni_prenotazioni.application.usecases.impl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1/prenotazioni")
public class PrenotazioneController {

    private final GestionePrenotazione gestionePrenotazione;
    private final IPrenotazioneFissa prenotazioneFissa;
    private final IPrenotazioneSingoloGiorno prenotazioneSingoloGiorno;
    private final IModCreaPrenotazione modCreaPrenotazione;
    private final IApprovaPrenotazione approvaPrenotazione;
    private final ICheckCapienza checkCapienza;

    @Autowired
    public PrenotazioneController(GestionePrenotazione gestionePrenotazione,
                                  IPrenotazioneSingoloGiorno prenotazioneSingoloGiorno ,
                                  IPrenotazioneFissa prenotazioneFissa,
                                  IModCreaPrenotazione modCreaPrenotazione,
                                  IApprovaPrenotazione approvaPrenotazione,
                                  ICheckCapienza checkCapienza) {
        this.gestionePrenotazione = gestionePrenotazione;
        this.prenotazioneFissa = prenotazioneFissa;
        this.modCreaPrenotazione = modCreaPrenotazione;
        this.approvaPrenotazione = approvaPrenotazione;
        this.prenotazioneSingoloGiorno = prenotazioneSingoloGiorno;
        this.checkCapienza = checkCapienza;
    }

    @GetMapping("/{id}")
    public ResponseEntity<PrenotazioneDTO> getPrenotazioneById(@PathVariable Long id) {
        PrenotazioneDTO prenotazione = gestionePrenotazione.getPrenotazioneById(id);
        return ResponseEntity.ok(prenotazione);
    }

    @GetMapping
    public ResponseEntity<List<PrenotazioneDTO>> getAllPrenotazioni() {
        List<PrenotazioneDTO> prenotazioni = gestionePrenotazione.getAllPrenotazioni();
        return ResponseEntity.ok(prenotazioni);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PrenotazioneDTO> updatePrenotazione(@PathVariable Long id, @RequestBody PrenotazioneDTO prenotazione) {
        PrenotazioneDTO updatedPrenotazione = gestionePrenotazione.updatePrenotazione(id, prenotazione);
        return ResponseEntity.ok(updatedPrenotazione);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePrenotazione(@PathVariable Long id) {
        gestionePrenotazione.deletePrenotazione(id);
        return ResponseEntity.noContent().build();
    }
//  prenotazione fissa ciclica per il giorno della settimana corrispondente a quella della prenotazione
    @PostMapping("/prenotazione_fissa")
    public ResponseEntity<Void> prenotaGiornoFisso(@RequestBody PrenotazioneDTO PrenotazioneDTO) {
        List<PrenotazioneDTO> prenotazioni = prenotazioneFissa.creaPrenotazioniRicorrenti(PrenotazioneDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/moderatore")
    public ResponseEntity<PrenotazioneDTO> modCreaPrenotazione(@RequestBody PrenotazioneDTO PrenotazioneDTO) {
        PrenotazioneDTO newPrenotazione = modCreaPrenotazione.createPrenotazione(PrenotazioneDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @PutMapping("/approvazione/{id}")
    public ResponseEntity<PrenotazioneDTO> approvaPrenotazione(@PathVariable Long id, @RequestBody PrenotazioneDTO prenotazione) {
        PrenotazioneDTO approvedPrenotazione = approvaPrenotazione.approvaPrenotazione(id);
        return ResponseEntity.ok(approvedPrenotazione);
    }
//  logica per effettuare una prenotazione singola per un giorno specifico
    @PostMapping("/prenotazione_singolo_giorno")
    public ResponseEntity<PrenotazioneDTO> prenotaPerSingoloGiorno(@RequestBody PrenotazioneDTO PrenotazioneDTO) {
        PrenotazioneDTO newPrenotazione = prenotazioneSingoloGiorno.prenotaPerSingoloGiorno(PrenotazioneDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/capienza/{id}")
    public ResponseEntity<Boolean> checkCapienza(@RequestBody StanzaDTO stanzaDTO, Date data) {
        return ResponseEntity.ok(checkCapienza.isOver(stanzaDTO, data));
    }
}

