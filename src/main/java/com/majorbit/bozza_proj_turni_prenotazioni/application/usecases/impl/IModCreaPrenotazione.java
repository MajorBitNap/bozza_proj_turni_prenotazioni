package com.majorbit.bozza_proj_turni_prenotazioni.application.usecases.impl;

import com.majorbit.bozza_proj_turni_prenotazioni.application.dto.PrenotazioneDTO;
import com.majorbit.bozza_proj_turni_prenotazioni.application.mapper.PrenotazioneMapper;
import com.majorbit.bozza_proj_turni_prenotazioni.domain.model.Prenotazione;
import com.majorbit.bozza_proj_turni_prenotazioni.domain.repository.PrenotazioneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IModCreaPrenotazione {

    @Autowired
    private PrenotazioneRepository prenotazioneRepository;

    @Autowired
    private PrenotazioneMapper prenotazioneMapper;

    public PrenotazioneDTO createPrenotazione(PrenotazioneDTO prenotazioneDTO) {
        Prenotazione prenotazione = PrenotazioneMapper.toEntity(prenotazioneDTO);
        prenotazione.setStato("Approvata");
        Prenotazione savedPrenotazione = prenotazioneRepository.save(prenotazione);
        return PrenotazioneMapper.toDTO(savedPrenotazione);
    }
}
