package com.majorbit.bozza_proj_turni_prenotazioni.application.mapper;

import com.majorbit.bozza_proj_turni_prenotazioni.application.dto.StanzaDTO;
import com.majorbit.bozza_proj_turni_prenotazioni.domain.model.Stanza;
import com.majorbit.bozza_proj_turni_prenotazioni.domain.repository.PianoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StanzaMapper {

    private final PianoRepository pianoRepository;

    @Autowired
    public StanzaMapper(PianoRepository pianoRepository) {
        this.pianoRepository = pianoRepository;
    }

    public StanzaDTO toDTO(Stanza stanza) {
        return new StanzaDTO(
                stanza.getNome(),
                stanza.getCapienza(),
                stanza.getPiano().getId()
        );
    }

    public Stanza toEntity(StanzaDTO stanzaDTO) {
        Stanza stanza = new Stanza();
        stanza.setNome(stanzaDTO.getNome());
        stanza.setCapienza(stanzaDTO.getCapienza());
        stanza.setPiano(pianoRepository.findById(stanzaDTO.getPiano()).orElseThrow());
        return stanza;
    }

}
