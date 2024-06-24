package com.majorbit.bozza_proj_turni_prenotazioni.application.mapper;

import com.majorbit.bozza_proj_turni_prenotazioni.application.dto.StanzaDTO;
import com.majorbit.bozza_proj_turni_prenotazioni.domain.model.Posto;
import com.majorbit.bozza_proj_turni_prenotazioni.domain.model.Stanza;
import com.majorbit.bozza_proj_turni_prenotazioni.domain.repository.PianoRepository;
import com.majorbit.bozza_proj_turni_prenotazioni.domain.repository.PostoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class StanzaMapper {

    private final PianoRepository pianoRepository;
    private final PostoRepository postoRepository;

    public StanzaDTO toDTO(Stanza stanza) {
        List<Posto> posti = stanza.getPosti();
        List<Integer> postiId = new ArrayList<>();
        posti.forEach((posto) -> postiId.add(posto.getId()));
        return new StanzaDTO(
                stanza.getNome(),
                stanza.getCapienza(),
                stanza.getPiano().getId(),
                postiId
        );
    }

    public Stanza toEntity(StanzaDTO stanzaDTO) {
        List<Integer> postiId = stanzaDTO.getPosti();
        List<Posto> posti = new ArrayList<>();
        if (!Objects.equals(null, postiId)) {
            postiId.forEach((id) -> posti.add(postoRepository.findById(id).orElseThrow()));
        }
        var stanza = new Stanza();
        stanza.setNome(stanzaDTO.getNome());
        stanza.setCapienza(stanzaDTO.getCapienza());
        stanza.setPiano(pianoRepository.findById(stanzaDTO.getPiano()).orElseThrow());
        stanza.setPosti(posti);
        return stanza;
    }

}
