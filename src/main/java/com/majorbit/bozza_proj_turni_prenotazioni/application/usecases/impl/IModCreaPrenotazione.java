package com.majorbit.bozza_proj_turni_prenotazioni.application.usecases.impl;

import com.majorbit.bozza_proj_turni_prenotazioni.application.dto.PrenotazioneDTO;
import com.majorbit.bozza_proj_turni_prenotazioni.application.mapper.PrenotazioneMapper;
import com.majorbit.bozza_proj_turni_prenotazioni.domain.model.Prenotazione;
import com.majorbit.bozza_proj_turni_prenotazioni.domain.repository.PrenotazioneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IModCreaPrenotazione {

    private final PrenotazioneRepository prenotazioneRepository;
    private final PrenotazioneMapper prenotazioneMapper;

    @Autowired
    public IModCreaPrenotazione(PrenotazioneRepository prenotazioneRepository,
                                PrenotazioneMapper prenotazioneMapper) {
        this.prenotazioneRepository = prenotazioneRepository;
        this.prenotazioneMapper = prenotazioneMapper;
    }

    public PrenotazioneDTO createPrenotazione(PrenotazioneDTO PrenotazioneDTO) {
        Prenotazione prenotazione = prenotazioneMapper.toEntity(PrenotazioneDTO);
        prenotazione.setStato("Approvata");
        Prenotazione savedPrenotazione = prenotazioneRepository.save(prenotazione);
        return prenotazioneMapper.toDTO(savedPrenotazione);
    }
}
