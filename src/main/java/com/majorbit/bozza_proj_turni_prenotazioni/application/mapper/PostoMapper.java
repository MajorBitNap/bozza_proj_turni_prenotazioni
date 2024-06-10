package com.majorbit.bozza_proj_turni_prenotazioni.application.mapper;

import com.majorbit.bozza_proj_turni_prenotazioni.application.dto.PostoDTO;
import com.majorbit.bozza_proj_turni_prenotazioni.domain.model.Posto;

public class PostoMapper {

    public static PostoDTO toDTO(Posto posto) {
        return new PostoDTO(
            posto.getId(),
            posto.getNome(),
            posto.isDisponibile(),
            posto.getStanzaId()
        );
    }

    public static Posto toEntity(PostoDTO postoDTO) {
        Posto posto = new Posto();
        posto.setId(postoDTO.id());
        posto.setDisponibile(postoDTO.disponibile());
        posto.setNome(postoDTO.nome());
        posto.setStanzaId(postoDTO.stanzaId());
        return posto;
    }
}