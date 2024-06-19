package com.majorbit.bozza_proj_turni_prenotazioni.application.usecases.impl;

import com.majorbit.bozza_proj_turni_prenotazioni.application.dto.PrenotazioneDTO;
import com.majorbit.bozza_proj_turni_prenotazioni.application.mapper.PostoMapper;
import com.majorbit.bozza_proj_turni_prenotazioni.application.mapper.PrenotazioneMapper;
import com.majorbit.bozza_proj_turni_prenotazioni.application.mapper.UtenteMapper;
import com.majorbit.bozza_proj_turni_prenotazioni.application.usecases.spec.PrenotazioneSingoloGiorno;
import com.majorbit.bozza_proj_turni_prenotazioni.domain.model.Prenotazione;
import com.majorbit.bozza_proj_turni_prenotazioni.domain.repository.PrenotazioneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IPrenotazioneSingoloGiorno implements PrenotazioneSingoloGiorno {

    public PrenotazioneRepository prenotazioneRepository;
    public PrenotazioneMapper prenotazioneMapper;
    public UtenteMapper utenteMapper;
    public PostoMapper postoMapper;

    @Autowired
    public IPrenotazioneSingoloGiorno(
            PrenotazioneRepository prenotazioneRepository,
            PrenotazioneMapper prenotazioneMapper,
            UtenteMapper utenteMapper,
            PostoMapper postoMapper
    ){
        this.prenotazioneRepository=prenotazioneRepository;
        this.prenotazioneMapper=prenotazioneMapper;
        this.utenteMapper = utenteMapper;
        this.postoMapper = postoMapper;
    }
    @Override
    public PrenotazioneDTO prenotaPerSingoloGiorno (PrenotazioneDTO prenotazioneDTO){
        Prenotazione prenotazione = new Prenotazione();
        prenotazione.setDataInizio(prenotazioneDTO.getDataInizio());
        prenotazione.setStato("INSERITA");
        prenotazione.setUtente(prenotazioneDTO.getUtente());
        prenotazione.setPosto(prenotazioneDTO.getPosto());
        prenotazioneRepository.save(prenotazione);
        return PrenotazioneMapper.toDTO(prenotazione);
    }
}
