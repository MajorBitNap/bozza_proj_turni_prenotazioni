package com.majorbit.bozza_proj_turni_prenotazioni.presentation.controller;

import com.majorbit.bozza_proj_turni_prenotazioni.application.dto.DateRequestDTO;
import com.majorbit.bozza_proj_turni_prenotazioni.application.dto.PostoDTO;
import com.majorbit.bozza_proj_turni_prenotazioni.application.dto.PrenotazioneDTO;
import com.majorbit.bozza_proj_turni_prenotazioni.application.usecases.impl.IPrenotazioneFissa;
import com.majorbit.bozza_proj_turni_prenotazioni.application.usecases.impl.IPrenotazioneSingola;
import com.majorbit.bozza_proj_turni_prenotazioni.application.usecases.spec.RecuperaPostiDisponibili;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequiredArgsConstructor
@PreAuthorize("hasRole('DIPENDENTE')")
@RequestMapping("/api/v1/dipendente")
public class DipendenteController {

    private final IPrenotazioneFissa prenotazioneFissa;
    private final IPrenotazioneSingola prenotazioneSingola;
    private final RecuperaPostiDisponibili recuperaPostiDisponibili;

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

    @GetMapping("/posti_disponbili")
    public ResponseEntity<List<PostoDTO>> getPostiDisponibili() {
        var postiDisponibili = recuperaPostiDisponibili.getPostiDisponibili();
        return ResponseEntity.ok(postiDisponibili);
    }

}
