package com.majorbit.bozza_proj_turni_prenotazioni.presentation.controller;
import com.majorbit.bozza_proj_turni_prenotazioni.application.dto.PrenotazioneDTO;

import com.majorbit.bozza_proj_turni_prenotazioni.application.service.GestionePrenotazione;
import com.majorbit.bozza_proj_turni_prenotazioni.application.usecases.impl.IApprovaPrenotazione;
import com.majorbit.bozza_proj_turni_prenotazioni.application.usecases.impl.IModCreaPrenotazione;
import com.majorbit.bozza_proj_turni_prenotazioni.application.usecases.impl.IPrenotazioneFissa;
import com.majorbit.bozza_proj_turni_prenotazioni.application.usecases.impl.IPrenotazioneSingoloGiorno;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@RestController
@RequestMapping("/api/v1/prenotazioni")
public class PrenotazioneController {

    private final GestionePrenotazione gestionePrenotazione;

    private final IPrenotazioneFissa prenotazioneFissa;

    private final IPrenotazioneSingoloGiorno prenotazioneSingoloGiorno;

    private final IModCreaPrenotazione modCreaPrenotazione;

    private final IApprovaPrenotazione approvaPrenotazione;

    @Autowired
    public PrenotazioneController(GestionePrenotazione gestionePrenotazione, IPrenotazioneSingoloGiorno prenotazioneSingoloGiorno ,
                                  IPrenotazioneFissa prenotazioneFissa,
                                  IModCreaPrenotazione modCreaPrenotazione,
                                  IApprovaPrenotazione approvaPrenotazione) {
        this.gestionePrenotazione = gestionePrenotazione;
        this.prenotazioneFissa = prenotazioneFissa;
        this.modCreaPrenotazione = modCreaPrenotazione;
        this.approvaPrenotazione = approvaPrenotazione;
        this.prenotazioneSingoloGiorno = prenotazioneSingoloGiorno;
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

    @PostMapping
    public ResponseEntity<PrenotazioneDTO> createPrenotazione(@RequestBody PrenotazioneDTO prenotazione) {
        PrenotazioneDTO newPrenotazione = gestionePrenotazione.createPrenotazione(prenotazione);
        return ResponseEntity.status(HttpStatus.CREATED).build();
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
    public ResponseEntity<Void> prenotaGiornoFisso(@RequestBody PrenotazioneDTO prenotazioneDTO) {
        List<PrenotazioneDTO> prenotazioni = creaPrenotazioniRicorrenti(prenotazioneDTO);
        prenotazioni.forEach(prenotazioneFissa::prenotaGiornoFisso);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
//  logica per calcolare le prenotazioni ricorrenti
    private List<PrenotazioneDTO> creaPrenotazioniRicorrenti(PrenotazioneDTO prenotazioneDTO) {
        List<PrenotazioneDTO> prenotazioni = new ArrayList<>();
        Calendar cal = Calendar.getInstance();
        cal.setTime(prenotazioneDTO.getDataInizio());

        while (cal.getTime().before(prenotazioneDTO.getDataFine())) {
            if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.THURSDAY) {
                PrenotazioneDTO nuovaPrenotazione = new PrenotazioneDTO();
                nuovaPrenotazione.setDataInizio(new Date(cal.getTimeInMillis()));
                nuovaPrenotazione.setDataFine(prenotazioneDTO.getDataFine());
                nuovaPrenotazione.setStato(prenotazioneDTO.getStato());
                nuovaPrenotazione.setPosto(prenotazioneDTO.getPosto());
                nuovaPrenotazione.setUtente(prenotazioneDTO.getUtente());
                prenotazioni.add(nuovaPrenotazione);
            }
            cal.add(Calendar.DAY_OF_MONTH, 1);
        }
        return prenotazioni;
    }

    @PostMapping("/moderatore")
    public ResponseEntity<PrenotazioneDTO> modCreaPrenotazione(@RequestBody PrenotazioneDTO prenotazioneDTO) {
        PrenotazioneDTO newPrenotazione = modCreaPrenotazione.createPrenotazione(prenotazioneDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @PutMapping("/approvazione/{id}")
    public ResponseEntity<PrenotazioneDTO> approvaPrenotazione(@PathVariable Long id, @RequestBody PrenotazioneDTO prenotazione) {
        PrenotazioneDTO approvedPrenotazione = approvaPrenotazione.approvaPrenotazione(id);
        return ResponseEntity.ok(approvedPrenotazione);
    }

    @PostMapping("/prenotazione_singolo_giorno")
    public ResponseEntity<PrenotazioneDTO> prenotaPerSingoloGiorno(@RequestBody PrenotazioneDTO prenotazioneDTO) {
        PrenotazioneDTO newPrenotazione = prenotazioneSingoloGiorno.prenotaPerSingoloGiorno(prenotazioneDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}

