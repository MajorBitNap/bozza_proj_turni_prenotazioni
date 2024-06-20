package com.majorbit.bozza_proj_turni_prenotazioni.application.usecases.impl;

import com.majorbit.bozza_proj_turni_prenotazioni.application.dto.StanzaDTO;
import com.majorbit.bozza_proj_turni_prenotazioni.application.mapper.PrenotazioneMapper;
import com.majorbit.bozza_proj_turni_prenotazioni.application.mapper.StanzaMapper;
import com.majorbit.bozza_proj_turni_prenotazioni.application.usecases.spec.CheckCapienza;
import com.majorbit.bozza_proj_turni_prenotazioni.domain.model.Prenotazione;
import com.majorbit.bozza_proj_turni_prenotazioni.domain.model.Stanza;
import com.majorbit.bozza_proj_turni_prenotazioni.domain.repository.PrenotazioneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.List;

@Component
public class ICheckCapienza implements CheckCapienza {

    private final PrenotazioneRepository prenotazioneRepository;
    private final StanzaMapper stanzaMapper;

    @Autowired
    public ICheckCapienza(PrenotazioneRepository prenotazioneRepository,
                          StanzaMapper stanzaMapper) {
        this.prenotazioneRepository = prenotazioneRepository;
        this.stanzaMapper = stanzaMapper;
    }

    public boolean isOver(StanzaDTO stanzaDTO, Date data) {
        Stanza stanza = stanzaMapper.toEntity(stanzaDTO);
        List<Prenotazione> prenotazioni = prenotazioneRepository.findByStanzaAndData(stanza.getId(), data);
        if (prenotazioni.size() < (stanza.getCapienza() * 0.8)) {
            return false;
        }
        return true;
    }
}
