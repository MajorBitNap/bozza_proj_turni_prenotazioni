package com.majorbit.bozza_proj_turni_prenotazioni.application.mapper;

import com.majorbit.bozza_proj_turni_prenotazioni.application.dto.PostoDTO;
import com.majorbit.bozza_proj_turni_prenotazioni.domain.model.Posto;
import com.majorbit.bozza_proj_turni_prenotazioni.domain.repository.StanzaRepository;
import com.majorbit.bozza_proj_turni_prenotazioni.util.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PostoMapper {

    private final StanzaRepository stanzaRepository;

    public PostoDTO toDTO(Posto posto) {
        return PostoDTO.builder()
                .nome(posto.getNome())
                .disponibile(posto.isDisponibile())
                .stanza(posto.getStanza().getId())
                .build();
    }

    public Posto toEntity(PostoDTO postoDTO) {
        return Posto.builder()
                .nome(postoDTO.getNome())
                .disponibile(postoDTO.isDisponibile())
                .stanza(stanzaRepository.findById(postoDTO.getStanza()).orElseThrow(() -> new ResourceNotFoundException("Resource not found")))
                .build();
    }
}
