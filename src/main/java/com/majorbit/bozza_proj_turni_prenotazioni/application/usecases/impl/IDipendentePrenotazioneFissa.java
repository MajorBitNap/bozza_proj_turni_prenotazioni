package com.majorbit.bozza_proj_turni_prenotazioni.application.usecases.impl;

import com.majorbit.bozza_proj_turni_prenotazioni.application.dto.PrenotazioneDTO;
import com.majorbit.bozza_proj_turni_prenotazioni.application.mapper.PrenotazioneMapper;
import com.majorbit.bozza_proj_turni_prenotazioni.application.service.EmailService;
import com.majorbit.bozza_proj_turni_prenotazioni.application.usecases.spec.DipendentePrenotazioneFissa;
import com.majorbit.bozza_proj_turni_prenotazioni.domain.model.Prenotazione;
import com.majorbit.bozza_proj_turni_prenotazioni.domain.model.Stato;
import com.majorbit.bozza_proj_turni_prenotazioni.domain.repository.PostoRepository;
import com.majorbit.bozza_proj_turni_prenotazioni.domain.repository.PrenotazioneRepository;
import com.majorbit.bozza_proj_turni_prenotazioni.domain.repository.UtenteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

@Service
@RequiredArgsConstructor
public class IDipendentePrenotazioneFissa implements DipendentePrenotazioneFissa {

    private final PrenotazioneRepository prenotazioneRepository;
    private final UtenteRepository utenteRepository;
    private final PostoRepository postoRepository;
    private final PrenotazioneMapper prenotazioneMapper;
    private final EmailService emailService;
//  logica per prenotare un posto per una Data
    @Override
    public List<PrenotazioneDTO> creaPrenotazioniRicorrenti(PrenotazioneDTO prenotazioneDTO) {

        List<PrenotazioneDTO> prenotazioni = new ArrayList<>();
        var cal = Calendar.getInstance();
        cal.setTime(prenotazioneDTO.getDataInizio());
        var utente = utenteRepository.findById(prenotazioneDTO.getUtente()).orElseThrow();
        var posto = postoRepository.findById(prenotazioneDTO.getPosto()).orElseThrow();
        int conteggioPrenotazioni = 1;
        int giornoDellaSettimanaDesiderato = cal.get(Calendar.DAY_OF_WEEK);
        var nomeGiornoDellaSettimanaDesiderato = cal.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault());

        while (cal.getTime().before(prenotazioneDTO.getDataFine()) || cal.get(Calendar.DAY_OF_WEEK) == giornoDellaSettimanaDesiderato) {
            if (cal.get(Calendar.DAY_OF_WEEK) == giornoDellaSettimanaDesiderato) {
                var prenotazione = Prenotazione.builder()
                        .dataInizio(new Date(cal.getTimeInMillis()))
                        .dataFine(prenotazioneDTO.getDataFine())
                        .stato(Stato.INSERITA)
                        .utente(utente)
                        .posto(posto)
                        .build();
                prenotazioneRepository.save(prenotazione);
                conteggioPrenotazioni++;
                var savedPrenotazioneDTO = prenotazioneMapper.toDTO(prenotazione);
                prenotazioni.add(savedPrenotazioneDTO);
            }
            cal.add(Calendar.DAY_OF_MONTH, 1);
        }
        emailService.sendEmail(
                utente.getEmail(),
                "Prenotazione Ripetuta Effettuata",
                "Sono appena state inserite " + conteggioPrenotazioni + " prenotazioni per il " + utente.getRuolo().toString().toLowerCase()
                        + " " + utente.getNome() + " " + utente.getCognome() + " per ogni "
                        + nomeGiornoDellaSettimanaDesiderato + " dal giorno " + prenotazioneDTO.getDataInizio() + " al giorno "
                        + prenotazioneDTO.getDataFine());
        return prenotazioni;
    }
}