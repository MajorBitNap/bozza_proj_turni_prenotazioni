package com.majorbit.bozza_proj_turni_prenotazioni.application.usecases.impl;

import com.majorbit.bozza_proj_turni_prenotazioni.application.dto.PrenotazioneDTO;
import com.majorbit.bozza_proj_turni_prenotazioni.application.mapper.PrenotazioneMapper;
import com.majorbit.bozza_proj_turni_prenotazioni.application.service.EmailService;
import com.majorbit.bozza_proj_turni_prenotazioni.application.usecases.spec.DipendentePrenotazioneSingola;
import com.majorbit.bozza_proj_turni_prenotazioni.domain.model.Prenotazione;
import com.majorbit.bozza_proj_turni_prenotazioni.domain.model.Stato;
import com.majorbit.bozza_proj_turni_prenotazioni.domain.repository.PostoRepository;
import com.majorbit.bozza_proj_turni_prenotazioni.domain.repository.PrenotazioneRepository;
import com.majorbit.bozza_proj_turni_prenotazioni.domain.repository.UtenteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class IDipendentePrenotazioneSingola implements DipendentePrenotazioneSingola {

    private final PrenotazioneRepository prenotazioneRepository;
    private final UtenteRepository utenteRepository;
    private final PostoRepository postoRepository;
    private final PrenotazioneMapper prenotazioneMapper;
    private final EmailService emailService;
    private final ICheckCapienza checkCapienza;

    @Override
    public PrenotazioneDTO creaPrenotazione(PrenotazioneDTO prenotazioneDTO){
        var utente = utenteRepository.findById(prenotazioneDTO.getUtente()).orElseThrow();
        var posto = postoRepository.findById(prenotazioneDTO.getPosto()).orElseThrow();
        var prenotazione = Prenotazione.builder()
                .dataInizio(prenotazioneDTO.getDataInizio())
                .dataFine(prenotazioneDTO.getDataFine())
                .stato(Stato.INSERITA)
                .utente(utente)
                .posto(posto)
                .build();
        prenotazioneRepository.save(prenotazione);
        emailService.sendEmail(
                utente.getEmail(),
                "Prenotazione Singola Inserita",
                "E' stata inserita una prenotazione per il " + utente.getRuolo().toString().toLowerCase()
                        + " " + utente.getNome() + " " + utente.getCognome() + " nel giorno "
                        + prenotazioneDTO.getDataInizio());
        checkCapienza.isOver(posto.getStanza().getId(), prenotazioneDTO.getDataInizio(), prenotazioneDTO.getDataFine());
        return prenotazioneMapper.toDTO(prenotazione);
    }
}
