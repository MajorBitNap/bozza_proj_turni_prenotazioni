package com.majorbit.bozza_proj_turni_prenotazioni.application.usecases.impl;

import com.majorbit.bozza_proj_turni_prenotazioni.application.dto.PrenotazioneDTO;
import com.majorbit.bozza_proj_turni_prenotazioni.application.mapper.PrenotazioneMapper;
import com.majorbit.bozza_proj_turni_prenotazioni.application.service.EmailService;
import com.majorbit.bozza_proj_turni_prenotazioni.domain.repository.PrenotazioneRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class IModCreaPrenotazione {

    private final PrenotazioneRepository prenotazioneRepository;
    private final PrenotazioneMapper prenotazioneMapper;
    private final EmailService emailService;

    public PrenotazioneDTO createPrenotazione(PrenotazioneDTO prenotazioneDTO) {
        var prenotazione = prenotazioneMapper.toEntity(prenotazioneDTO);
        var utente = prenotazione.getUtente();
        prenotazione.setStato("Approvata");
        var savedPrenotazione = prenotazioneRepository.save(prenotazione);
        emailService.sendEmail(
                utente.getEmail(),
                "Prenotazione Singola Effettuata dal Moderatore",
                "E' stata inserita una prenotazione per il " + utente.getRuolo().toString().toLowerCase()
                        + " " + utente.getNome() + " " + utente.getCognome() + " nel giorno "
                        + prenotazioneDTO.getDataInizio() + " da un Moderatore");
        return prenotazioneMapper.toDTO(savedPrenotazione);
    }
}
