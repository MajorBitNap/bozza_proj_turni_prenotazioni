package com.majorbit.bozza_proj_turni_prenotazioni.application.usecases.impl;

import com.majorbit.bozza_proj_turni_prenotazioni.application.dto.StanzaDTO;
import com.majorbit.bozza_proj_turni_prenotazioni.application.mapper.StanzaMapper;
import com.majorbit.bozza_proj_turni_prenotazioni.application.service.EmailService;
import com.majorbit.bozza_proj_turni_prenotazioni.application.usecases.spec.CheckCapienza;
import com.majorbit.bozza_proj_turni_prenotazioni.domain.model.Posto;
import com.majorbit.bozza_proj_turni_prenotazioni.domain.model.Prenotazione;
import com.majorbit.bozza_proj_turni_prenotazioni.domain.repository.PrenotazioneRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ICheckCapienza implements CheckCapienza {

    private final PrenotazioneRepository prenotazioneRepository;
    private final StanzaMapper stanzaMapper;
    private final EmailService emailService;
//  datainizio e fine in base alla prenotazione dell'utente
    public boolean isOver(StanzaDTO stanzaDTO, Date dataInizio, Date  datafine) {
        var stanza = stanzaMapper.toEntity(stanzaDTO);
        List<Posto> posti = stanza.getPosti();
        List<Prenotazione> prenotazioni = new ArrayList<>();
        for (int i = 0; i < posti.size(); i++) {
            prenotazioni = prenotazioneRepository
                    .findPrenotazioniInDateRange(posti.get(i), dataInizio, datafine);
        }
//      controllo per capienza superiore o meno all'80%
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
