package com.majorbit.bozza_proj_turni_prenotazioni.application.usecases.impl;

import com.majorbit.bozza_proj_turni_prenotazioni.application.dto.PostoDTO;
import com.majorbit.bozza_proj_turni_prenotazioni.application.dto.PrenotazioneDTO;
import com.majorbit.bozza_proj_turni_prenotazioni.application.dto.UtenteDTO;
import com.majorbit.bozza_proj_turni_prenotazioni.application.mapper.PostoMapper;
import com.majorbit.bozza_proj_turni_prenotazioni.application.mapper.PrenotazioneMapper;
import com.majorbit.bozza_proj_turni_prenotazioni.application.mapper.UtenteMapper;
import com.majorbit.bozza_proj_turni_prenotazioni.application.usecases.spec.PrenotazioneFissa;
import com.majorbit.bozza_proj_turni_prenotazioni.domain.model.Posto;
import com.majorbit.bozza_proj_turni_prenotazioni.domain.model.Prenotazione;
import com.majorbit.bozza_proj_turni_prenotazioni.domain.model.Utente;
import com.majorbit.bozza_proj_turni_prenotazioni.domain.repository.PrenotazioneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;

@Service
public class IPrenotazioneFissa implements PrenotazioneFissa {

    public PrenotazioneRepository prenotazioneRepository;

    public PrenotazioneMapper prenotazioneMapper;

    public UtenteMapper utenteMapper;

    public PostoMapper postoMapper;

    @Autowired
    public  IPrenotazioneFissa(
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


    @Override
    public PrenotazioneDTO prenotazioneFissa(Date dataInizio, UtenteDTO utenteDTO, PostoDTO postoDTO) {
        Utente utente = UtenteMapper.toEntity(utenteDTO);
        Posto posto = PostoMapper.toEntity(postoDTO);
        Prenotazione prenotazione = new Prenotazione();
        prenotazione.setDataFine(dataInizio);
        prenotazione.setStato("INSERITA");
        prenotazione.setUtente(utente);
        prenotazione.setPosto(posto);
        prenotazioneRepository.save(prenotazione);
        return PrenotazioneMapper.toDTO(prenotazione);
    }
}
