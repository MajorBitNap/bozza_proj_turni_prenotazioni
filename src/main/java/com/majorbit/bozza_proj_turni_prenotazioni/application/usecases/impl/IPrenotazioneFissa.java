package com.majorbit.bozza_proj_turni_prenotazioni.application.usecases.impl;

import com.majorbit.bozza_proj_turni_prenotazioni.application.dto.PrenotazioneDTO;
import com.majorbit.bozza_proj_turni_prenotazioni.application.mapper.PostoMapper;
import com.majorbit.bozza_proj_turni_prenotazioni.application.mapper.PrenotazioneMapper;
import com.majorbit.bozza_proj_turni_prenotazioni.application.mapper.UtenteMapper;
import com.majorbit.bozza_proj_turni_prenotazioni.application.service.EmailService;
import com.majorbit.bozza_proj_turni_prenotazioni.application.usecases.spec.PrenotazioneFissa;
import com.majorbit.bozza_proj_turni_prenotazioni.domain.model.Posto;
import com.majorbit.bozza_proj_turni_prenotazioni.domain.model.Prenotazione;
import com.majorbit.bozza_proj_turni_prenotazioni.domain.model.Utente;
import com.majorbit.bozza_proj_turni_prenotazioni.domain.repository.PostoRepository;
import com.majorbit.bozza_proj_turni_prenotazioni.domain.repository.PrenotazioneRepository;
import com.majorbit.bozza_proj_turni_prenotazioni.domain.repository.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

@Service
public class IPrenotazioneFissa implements PrenotazioneFissa {

    private final PrenotazioneRepository prenotazioneRepository;
    private final UtenteRepository utenteRepository;
    private final PostoRepository postoRepository;
    private final PrenotazioneMapper prenotazioneMapper;
    private final EmailService emailService;

    @Autowired
    public IPrenotazioneFissa(
            PrenotazioneRepository prenotazioneRepository,
            UtenteRepository utenteRepository,
            PostoRepository postoRepository,
            PrenotazioneMapper prenotazioneMapper,
            EmailService emailService
    ) {
        this.prenotazioneRepository=prenotazioneRepository;
        this.utenteRepository = utenteRepository;
        this.postoRepository = postoRepository;
        this.prenotazioneMapper = prenotazioneMapper;
        this.emailService = emailService;
    }

//  logica per prenotare un posto per una Data
    @Override
    public List<PrenotazioneDTO> creaPrenotazioniRicorrenti(PrenotazioneDTO prenotazioneDTO) {

        List<PrenotazioneDTO> prenotazioni = new ArrayList<>();
        Calendar cal = Calendar.getInstance();
        cal.setTime(prenotazioneDTO.getDataInizio());
        Utente utente = utenteRepository.findById(prenotazioneDTO.getUtente()).orElseThrow();
        Posto posto = postoRepository.findById(prenotazioneDTO.getPosto()).orElseThrow();
        int c = 1;
        int giornoDellaSettimanaDesiderato = cal.get(Calendar.DAY_OF_WEEK);
        String nomeGiornoDellaSettimanaDesiderato = cal.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault());

        while (cal.getTime().before(prenotazioneDTO.getDataFine()) || cal.get(Calendar.DAY_OF_WEEK) == giornoDellaSettimanaDesiderato) {
            if (cal.get(Calendar.DAY_OF_WEEK) == giornoDellaSettimanaDesiderato) {
                Prenotazione prenotazione = new Prenotazione();
                prenotazione.setDataInizio(new Date(cal.getTimeInMillis()));
                prenotazione.setDataFine(prenotazioneDTO.getDataFine());
                prenotazione.setStato("INSERITA");
                prenotazione.setUtente(utente);
                prenotazione.setPosto(posto);
                prenotazioneRepository.save(prenotazione);
                c++;
                PrenotazioneDTO savedPrenotazioneDTO = prenotazioneMapper.toDTO(prenotazione);
                prenotazioni.add(savedPrenotazioneDTO);
            }
            cal.add(Calendar.DAY_OF_MONTH, 1);
        }
        emailService.sendEmail(
                utente.getEmail(),
                "Prenotazione Ripetuta Effettuata",
                "Sono appena state inserite " + c + " prenotazioni per il " + utente.getRuolo().toString().toLowerCase() + " " + utente.getNome() + " " + utente.getCognome() + " per ogni " + nomeGiornoDellaSettimanaDesiderato + " dal giorno "+ prenotazioneDTO.getDataInizio() + " al giorno " + prenotazioneDTO.getDataFine());
        return prenotazioni;
    }
}