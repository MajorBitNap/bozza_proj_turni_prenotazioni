package com.majorbit.bozza_proj_turni_prenotazioni.application.mapper;

import com.majorbit.bozza_proj_turni_prenotazioni.application.dto.StanzaDTO;
import com.majorbit.bozza_proj_turni_prenotazioni.domain.model.Stanza;
import org.springframework.stereotype.Component;

@Component
public class StanzaMapper {

    public static StanzaDTO toDTO(Stanza stanza) {
        return new StanzaDTO(
                stanza.getNome(),
                stanza.getCapienza(),
                stanza.getPiano()
        );
    }

    public static Stanza toEntity(StanzaDTO stanzaDTO) {
        Stanza stanza = new Stanza();
        stanza.setNome(stanzaDTO.getNome());
        stanza.setCapienza(stanzaDTO.getCapienza());
        stanza.setPiano(stanzaDTO.getPiano());
        return stanza;
    }

}
