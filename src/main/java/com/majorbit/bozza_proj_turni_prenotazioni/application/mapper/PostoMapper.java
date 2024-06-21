package com.majorbit.bozza_proj_turni_prenotazioni.application.mapper;

import com.majorbit.bozza_proj_turni_prenotazioni.application.dto.PostoDTO;
import com.majorbit.bozza_proj_turni_prenotazioni.domain.model.Posto;
import com.majorbit.bozza_proj_turni_prenotazioni.domain.repository.StanzaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PostoMapper {

    private final StanzaRepository stanzaRepository;

    public PostoDTO toDTO(Posto posto) {
        return new PostoDTO(
            posto.getNome(),
            posto.isDisponibile(),
            posto.getStanza().getId()
        );
    }

    public Posto toEntity(PostoDTO postoDTO) {
        Posto posto = new Posto();
        posto.setDisponibile(postoDTO.isDisponibile());
        posto.setNome(postoDTO.getNome());
        posto.setStanza(stanzaRepository.findById(postoDTO.getStanza()).orElseThrow());
        return posto;
    }
}
