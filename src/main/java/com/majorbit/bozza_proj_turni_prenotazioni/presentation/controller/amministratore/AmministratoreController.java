package com.majorbit.bozza_proj_turni_prenotazioni.presentation.controller.amministratore;

import com.majorbit.bozza_proj_turni_prenotazioni.application.dto.PrenotazioneDTO;
import com.majorbit.bozza_proj_turni_prenotazioni.application.usecases.impl.IDipendentePrenotazioneFissa;
import com.majorbit.bozza_proj_turni_prenotazioni.application.usecases.impl.IDipendentePrenotazioneSingola;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@PreAuthorize("hasRole('AMMINISTRATORE')")
@RequestMapping("/api/v1/amministratore")
public class AmministratoreController {

    private final IDipendentePrenotazioneFissa prenotazioneFissa;
    private final IDipendentePrenotazioneSingola prenotazioneSingola;

    //  logica per effettuare una prenotazione singola per un giorno specifico
    @PostMapping("/prenotazione_singola")
    public ResponseEntity<PrenotazioneDTO> prenotaPerSingoloGiorno(@RequestBody PrenotazioneDTO prenotazione) {
        var newPrenotazione = prenotazioneSingola.creaPrenotazione(prenotazione);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    //  prenotazione fissa ciclica per il giorno della settimana corrispondente a quella della prenotazione
    @PostMapping("/prenotazione_fissa")
    public ResponseEntity<Void> prenotaGiornoFisso(@RequestBody PrenotazioneDTO prenotazione) {
        List<PrenotazioneDTO> prenotazioni = prenotazioneFissa.creaPrenotazioniRicorrenti(prenotazione);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
