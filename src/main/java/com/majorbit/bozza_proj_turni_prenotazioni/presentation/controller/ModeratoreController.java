package com.majorbit.bozza_proj_turni_prenotazioni.presentation.controller;

import com.majorbit.bozza_proj_turni_prenotazioni.application.dto.PrenotazioneDTO;
import com.majorbit.bozza_proj_turni_prenotazioni.application.usecases.impl.IApprovaPrenotazione;
import com.majorbit.bozza_proj_turni_prenotazioni.application.usecases.impl.IPrenotazioneFissa;
import com.majorbit.bozza_proj_turni_prenotazioni.application.usecases.impl.IPrenotazioneSingola;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/moderatore")
@RequiredArgsConstructor
@PreAuthorize("hasRole('MODERATORE')")
public class ModeratoreController {

    private final IPrenotazioneFissa prenotazioneFissa;
    private final IPrenotazioneSingola prenotazioneSingola;
    private final IApprovaPrenotazione approvaPrenotazione;

    //  prenotazione fissa ciclica per il giorno della settimana corrispondente a quella della prenotazione
    @PostMapping("/prenotazione_fissa")
    public ResponseEntity<Void> prenotaGiornoFisso(@RequestBody PrenotazioneDTO PrenotazioneDTO) {
        List<PrenotazioneDTO> prenotazioni = prenotazioneFissa.creaPrenotazioniRicorrenti(PrenotazioneDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    //  logica per effettuare una prenotazione singola per un giorno specifico
    @PostMapping("/prenotazione_singola")
    public ResponseEntity<PrenotazioneDTO> prenotaPerSingoloGiorno(@RequestBody PrenotazioneDTO PrenotazioneDTO) {
        var newPrenotazione = prenotazioneSingola.creaPrenotazione(PrenotazioneDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


    @PutMapping("/approvazione/{id}")
    public ResponseEntity<PrenotazioneDTO> approvaPrenotazione(@PathVariable Integer id) {
        var approvedPrenotazione = approvaPrenotazione.approvaPrenotazione(id);
        return ResponseEntity.ok(approvedPrenotazione);
    }

}
