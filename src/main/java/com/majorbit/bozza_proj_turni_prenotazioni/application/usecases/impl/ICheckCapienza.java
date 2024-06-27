package com.majorbit.bozza_proj_turni_prenotazioni.application.usecases.impl;

import com.majorbit.bozza_proj_turni_prenotazioni.application.service.EmailService;
import com.majorbit.bozza_proj_turni_prenotazioni.application.usecases.spec.CheckCapienza;
import com.majorbit.bozza_proj_turni_prenotazioni.domain.model.Posto;
import com.majorbit.bozza_proj_turni_prenotazioni.domain.model.Prenotazione;
import com.majorbit.bozza_proj_turni_prenotazioni.domain.model.Stanza;
import com.majorbit.bozza_proj_turni_prenotazioni.domain.repository.PrenotazioneRepository;
import com.majorbit.bozza_proj_turni_prenotazioni.domain.repository.SedeRepository;
import com.majorbit.bozza_proj_turni_prenotazioni.domain.repository.StanzaRepository;
import com.majorbit.bozza_proj_turni_prenotazioni.util.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ICheckCapienza implements CheckCapienza {

    private final PrenotazioneRepository prenotazioneRepository;
    private final StanzaRepository stanzaRepository;
    private final EmailService emailService;

    public boolean isOver(Integer idStanza, Date dataInizio, Date dataFine) {
        var stanza = stanzaRepository.findById(idStanza)
                .orElseThrow(() -> new ResourceNotFoundException("Resource not found"));
        List<Posto> posti = stanza.getPosti();
        List<Prenotazione> prenotazioni = new ArrayList<>();
        for (var posto : posti) {
            List<Prenotazione> prenotazioniPosto = prenotazioneRepository
                    .findPrenotazioniInDateRange(posto, dataInizio, dataFine);
            prenotazioni.addAll(prenotazioniPosto);
        }
        int capacitaStanza = stanza.getCapienza();
        double percentualeOccupata = (double) prenotazioni.size() / capacitaStanza * 100;
        if (percentualeOccupata < 80.0) {
            return false;
        }
        sendCapacityAlertEmail(stanza, percentualeOccupata);
        return true;
    }

    private void sendCapacityAlertEmail(Stanza stanza, double percentualeOccupata) {
        emailService.sendEmail(
                "antola.edoardo@gmail.com",
                "ALLERT CAPIENZA SUPERATA",
                "La stanza " + stanza.getNome() + " della di sede " + stanza.getPiano().getSede().getNome()
                        + " ha superato la sua capienza massima. Attualmente è occupata al "
                        + String.format("%.2f", percentualeOccupata) + "%."
                        + " La capacità massima della stanza è di : " + stanza.getCapienza()
        );
    }

}
