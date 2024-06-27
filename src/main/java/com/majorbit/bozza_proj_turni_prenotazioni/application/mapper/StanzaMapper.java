package com.majorbit.bozza_proj_turni_prenotazioni.application.mapper;

import com.majorbit.bozza_proj_turni_prenotazioni.application.dto.StanzaDTO;
import com.majorbit.bozza_proj_turni_prenotazioni.domain.model.Posto;
import com.majorbit.bozza_proj_turni_prenotazioni.domain.model.Stanza;
import com.majorbit.bozza_proj_turni_prenotazioni.domain.repository.PianoRepository;
import com.majorbit.bozza_proj_turni_prenotazioni.domain.repository.PostoRepository;
import com.majorbit.bozza_proj_turni_prenotazioni.util.exception.ResourceNotFoundException;
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
        return StanzaDTO.builder()
                .nome(stanza.getNome())
                .capienza(stanza.getCapienza())
                .piano(stanza.getPiano().getId())
                .posti(postiId)
                .build();
    }

    public Stanza toEntity(StanzaDTO stanzaDTO) {
        List<Integer> postiId = stanzaDTO.getPosti();
        List<Posto> posti = new ArrayList<>();
        if (!Objects.equals(null, postiId)) {
            postiId.forEach((id) -> posti.add(postoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Resource not found"))));
        }
        return Stanza.builder()
                .nome(stanzaDTO.getNome())
                .capienza(stanzaDTO.getCapienza())
                .piano(pianoRepository.findById(stanzaDTO.getPiano()).orElseThrow(() -> new ResourceNotFoundException("Resource not found")))
                .posti(posti)
                .build();
    }

}
