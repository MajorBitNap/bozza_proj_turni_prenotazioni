package com.majorbit.bozza_proj_turni_prenotazioni.application.usecases.impl;

import com.majorbit.bozza_proj_turni_prenotazioni.application.dto.PrenotazioneDTO;
import com.majorbit.bozza_proj_turni_prenotazioni.application.mapper.PrenotazioneMapper;
import com.majorbit.bozza_proj_turni_prenotazioni.application.mapper.UtenteMapper;
import com.majorbit.bozza_proj_turni_prenotazioni.application.service.EmailService;
import com.majorbit.bozza_proj_turni_prenotazioni.application.usecases.spec.ApprovaPrenotazione;
import com.majorbit.bozza_proj_turni_prenotazioni.domain.model.Prenotazione;
import com.majorbit.bozza_proj_turni_prenotazioni.domain.model.Utente;
import com.majorbit.bozza_proj_turni_prenotazioni.domain.repository.PrenotazioneRepository;
import com.majorbit.bozza_proj_turni_prenotazioni.util.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IApprovaPrenotazione implements ApprovaPrenotazione {

    private final PrenotazioneRepository prenotazioneRepository;
    private final EmailService emailService;
    private final UtenteMapper utenteMapper;

    @Autowired
    public IApprovaPrenotazione(PrenotazioneRepository prenotazioneRepository,
                                EmailService emailService,
                                UtenteMapper utenteMapper) {
        this.prenotazioneRepository = prenotazioneRepository;
        this.emailService = emailService;
        this.utenteMapper = utenteMapper;
    }

    @Override
    public PrenotazioneDTO approvaPrenotazione(Long id){
        Prenotazione prenotazione = prenotazioneRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Prenotazione not found"));
        prenotazione.setStato("Approvata");
        prenotazioneRepository.save(prenotazione);
        emailService.sendEmail(
                prenotazione.getUtente().getEmail(),
                "PRENOTAZIONE APPROVATA",
                "Gentile " + prenotazione.getUtente().getNome() + " " + prenotazione.getUtente().getCognome() + ", la sua prenotazione per il giorno " + prenotazione.getDataInizio() + " è stata accettata dal moderatore. \nBuon lavoro!"
        );
        return null;
    }

}
