package com.majorbit.bozza_proj_turni_prenotazioni.application.usecases.impl;

import com.majorbit.bozza_proj_turni_prenotazioni.application.dto.PrenotazioneDTO;
import com.majorbit.bozza_proj_turni_prenotazioni.application.mapper.PostoMapper;
import com.majorbit.bozza_proj_turni_prenotazioni.application.mapper.PrenotazioneMapper;
import com.majorbit.bozza_proj_turni_prenotazioni.application.mapper.UtenteMapper;
import com.majorbit.bozza_proj_turni_prenotazioni.application.usecases.spec.PrenotazioneFissa;
import com.majorbit.bozza_proj_turni_prenotazioni.domain.model.Prenotazione;
import com.majorbit.bozza_proj_turni_prenotazioni.domain.model.Utente;
import com.majorbit.bozza_proj_turni_prenotazioni.domain.repository.PrenotazioneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Service
public class IPrenotazioneFissa implements PrenotazioneFissa {

    public PrenotazioneRepository prenotazioneRepository;

    public PrenotazioneMapper prenotazioneMapper;

    public UtenteMapper utenteMapper;

    public PostoMapper postoMapper;

    @Autowired
    public IPrenotazioneFissa(
            PrenotazioneRepository prenotazioneRepository,
            PrenotazioneMapper prenotazioneMapper,
            UtenteMapper utenteMapper,
            PostoMapper postoMapper
    ) {
        this.prenotazioneRepository=prenotazioneRepository;
        this.prenotazioneMapper=prenotazioneMapper;
        this.utenteMapper = utenteMapper;
        this.postoMapper = postoMapper;
    }

//  logica per prenotare un posto per una Data
    @Override
    public List<PrenotazioneDTO> creaPrenotazioniRicorrenti(PrenotazioneDTO prenotazioneDTO) {

        List<PrenotazioneDTO> prenotazioni = new ArrayList<>();
        Calendar cal = Calendar.getInstance();
        cal.setTime(prenotazioneDTO.getDataInizio());

        int giornoDellaSettimanaDesiderato = cal.get(Calendar.DAY_OF_WEEK);

        while (cal.getTime().before(prenotazioneDTO.getDataFine()) || cal.get(Calendar.DAY_OF_WEEK) == giornoDellaSettimanaDesiderato) {
            if (cal.get(Calendar.DAY_OF_WEEK) == giornoDellaSettimanaDesiderato) {
                Prenotazione prenotazione = new Prenotazione();
                prenotazione.setDataInizio(new Date(cal.getTimeInMillis()));
                prenotazione.setDataFine(prenotazioneDTO.getDataFine());
                prenotazione.setStato("INSERITA");
                prenotazione.setUtente(UtenteMapper.toEntity(prenotazioneDTO.getUtente()));
                prenotazione.setPosto(PostoMapper.toEntity(prenotazioneDTO.getPosto()));
                prenotazioneRepository.save(prenotazione);
                PrenotazioneDTO savedPrenotazioneDTO = PrenotazioneMapper.toDTO(prenotazione);
                prenotazioni.add(savedPrenotazioneDTO);
            }
            cal.add(Calendar.DAY_OF_MONTH, 1);
        }
        return prenotazioni;
    }
}