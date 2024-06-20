package com.majorbit.bozza_proj_turni_prenotazioni.application.usecases.impl;

import com.majorbit.bozza_proj_turni_prenotazioni.application.dto.StanzaDTO;
import com.majorbit.bozza_proj_turni_prenotazioni.application.mapper.StanzaMapper;
import com.majorbit.bozza_proj_turni_prenotazioni.application.service.EmailService;
import com.majorbit.bozza_proj_turni_prenotazioni.application.usecases.spec.CheckCapienza;
import com.majorbit.bozza_proj_turni_prenotazioni.domain.model.Posto;
import com.majorbit.bozza_proj_turni_prenotazioni.domain.model.Prenotazione;
import com.majorbit.bozza_proj_turni_prenotazioni.domain.model.Stanza;
import com.majorbit.bozza_proj_turni_prenotazioni.domain.repository.PrenotazioneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Component
public class ICheckCapienza implements CheckCapienza {

    private final PrenotazioneRepository prenotazioneRepository;
    private final StanzaMapper stanzaMapper;
    private final EmailService emailService;

    @Autowired
    public ICheckCapienza(PrenotazioneRepository prenotazioneRepository,
                          StanzaMapper stanzaMapper,
                          EmailService emailService) {
        this.prenotazioneRepository = prenotazioneRepository;
        this.stanzaMapper = stanzaMapper;
        this.emailService = emailService;
    }

    public boolean isOver(StanzaDTO stanzaDTO, Date dataInizio, Date  datafine) {
        Stanza stanza = stanzaMapper.toEntity(stanzaDTO);
        List<Posto> posti = stanza.getPosti();
        List<Prenotazione> prenotazioni = new ArrayList<>();
        for (int i = 0; i < posti.size(); i++) {
            prenotazioni = prenotazioneRepository
                    .findPrenotazioniInDateRange(posti.get(i), dataInizio, datafine);
        }
        if (prenotazioni.size() < (stanza.getCapienza() * 0.8)) {
            return false;
        }
        emailService.sendEmail(
                "email.amministratore@majorbit.com",
                "ALLERT CAPIENZA 80% SUPERATA",
                "La stanza " + stanza.getNome() + " con id: " + stanza.getId()
                        + " ha superato l'80% della sua capienza massima che corrisponde a : "
                        + stanza.getCapienza()
        );
        return true;
    }
}
