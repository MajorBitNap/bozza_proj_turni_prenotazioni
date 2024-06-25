package com.majorbit.bozza_proj_turni_prenotazioni.presentation.controller;

import com.majorbit.bozza_proj_turni_prenotazioni.application.dto.PrenotazioneDTO;
import com.majorbit.bozza_proj_turni_prenotazioni.application.usecases.impl.IModCreaPrenotazione;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/prenotazione")
@RequiredArgsConstructor
@PreAuthorize("hasRole('MODERATORE')")
public class ModeratoreController {

    private final IModCreaPrenotazione modCreaPrenotazione;

    @PostMapping("/")
    public ResponseEntity<PrenotazioneDTO> modCreaPrenotazione(@RequestBody PrenotazioneDTO PrenotazioneDTO) {
        var newPrenotazione = modCreaPrenotazione.createPrenotazione(PrenotazioneDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
