package com.majorbit.bozza_proj_turni_prenotazioni.application.usecases.impl;

import com.majorbit.bozza_proj_turni_prenotazioni.application.dto.PrenotazioneDTO;
import com.majorbit.bozza_proj_turni_prenotazioni.application.mapper.UtenteMapper;
import com.majorbit.bozza_proj_turni_prenotazioni.application.service.EmailService;
import com.majorbit.bozza_proj_turni_prenotazioni.application.usecases.spec.ApprovaPrenotazione;
import com.majorbit.bozza_proj_turni_prenotazioni.domain.repository.PrenotazioneRepository;
import com.majorbit.bozza_proj_turni_prenotazioni.util.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class IApprovaPrenotazione implements ApprovaPrenotazione {

    private final PrenotazioneRepository prenotazioneRepository;
    private final EmailService emailService;
    private final UtenteMapper utenteMapper;

    @Override
    public PrenotazioneDTO approvaPrenotazione(Integer id){
        var prenotazione = prenotazioneRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Resource not found"));
        prenotazione.setStato("APPROVATA");
        prenotazioneRepository.save(prenotazione);
        emailService.sendEmail(
                prenotazione.getUtente().getEmail(),
                "PRENOTAZIONE APPROVATA",
                "Gentile " + prenotazione.getUtente().getNome() + " "
                        + prenotazione.getUtente().getCognome() + ", la sua prenotazione per il giorno "
                        + prenotazione.getDataInizio() + " è stata accettata dal moderatore. \nBuon lavoro!"
        );
        return null;
    }

}
