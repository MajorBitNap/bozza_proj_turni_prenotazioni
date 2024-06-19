package com.majorbit.bozza_proj_turni_prenotazioni.application.usecases.impl;

import com.majorbit.bozza_proj_turni_prenotazioni.application.dto.PrenotazioneDTO;
import com.majorbit.bozza_proj_turni_prenotazioni.application.mapper.PostoMapper;
import com.majorbit.bozza_proj_turni_prenotazioni.application.mapper.PrenotazioneMapper;
import com.majorbit.bozza_proj_turni_prenotazioni.application.mapper.UtenteMapper;
import com.majorbit.bozza_proj_turni_prenotazioni.application.usecases.spec.PrenotazioneSingoloGiorno;
import com.majorbit.bozza_proj_turni_prenotazioni.domain.model.Posto;
import com.majorbit.bozza_proj_turni_prenotazioni.domain.model.Prenotazione;
import com.majorbit.bozza_proj_turni_prenotazioni.domain.model.Utente;
import com.majorbit.bozza_proj_turni_prenotazioni.domain.repository.PostoRepository;
import com.majorbit.bozza_proj_turni_prenotazioni.domain.repository.PrenotazioneRepository;
import com.majorbit.bozza_proj_turni_prenotazioni.domain.repository.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IPrenotazioneSingoloGiorno implements PrenotazioneSingoloGiorno {

    private final PrenotazioneRepository prenotazioneRepository;

    private final UtenteRepository utenteRepository;

    private final PostoRepository postoRepository;

    @Autowired
    public IPrenotazioneSingoloGiorno(
            PrenotazioneRepository prenotazioneRepository,
            UtenteRepository utenteRepository,
            PostoRepository postoRepository
            ){
        this.prenotazioneRepository=prenotazioneRepository;
        this.utenteRepository = utenteRepository;
        this.postoRepository = postoRepository;
    }
    @Override
    public PrenotazioneDTO prenotaPerSingoloGiorno (PrenotazioneDTO prenotazioneDTO){
        Prenotazione prenotazione = new Prenotazione();
        Utente utente = utenteRepository.findById(prenotazioneDTO.getUtente()).orElseThrow();
        Posto posto = postoRepository.findById(prenotazioneDTO.getPosto()).orElseThrow();
        prenotazione.setDataInizio(prenotazioneDTO.getDataInizio());
        prenotazione.setStato("INSERITA");
        prenotazione.setUtente(utente);
        prenotazione.setPosto(posto);
        prenotazioneRepository.save(prenotazione);
        return PrenotazioneMapper.toDTO(prenotazione);
    }
}
