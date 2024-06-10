package com.majorbit.bozza_proj_turni_prenotazioni.application.mapper;

import com.majorbit.bozza_proj_turni_prenotazioni.application.dto.StanzaDTO;
import com.majorbit.bozza_proj_turni_prenotazioni.domain.model.Stanza;

public class StanzaMapper {

    public static StanzaDTO toDTO(Stanza stanza) {
        return new StanzaDTO(
                stanza.getId(),
                stanza.getNome(),
                stanza.getCapienza(),
                stanza.getPianoId()
        );
    }

    public static Stanza toEntity(StanzaDTO stanzaDTO) {
        Stanza stanza = new Stanza();
        stanza.setId(stanzaDTO.id());
        stanza.setNome(stanzaDTO.nome());
        stanza.setCapienza(stanzaDTO.capienza());
        stanza.setPianoId(stanzaDTO.pianoId());
        return stanza;
    }

}
