package com.majorbit.bozza_proj_turni_prenotazioni.application.mapper;

import com.majorbit.bozza_proj_turni_prenotazioni.application.dto.PostoDTO;
import com.majorbit.bozza_proj_turni_prenotazioni.domain.model.Posto;
import org.springframework.stereotype.Component;

@Component
public class PostoMapper {

    public static PostoDTO toDTO(Posto posto) {
        return new PostoDTO(
            posto.getNome(),
            posto.isDisponibile(),
            StanzaMapper.toDTO(posto.getStanza())
        );
    }

    public static Posto toEntity(PostoDTO postoDTO) {
        Posto posto = new Posto();
        posto.setDisponibile(postoDTO.isDisponibile());
        posto.setNome(postoDTO.getNome());
        posto.setStanza(StanzaMapper.toEntity(postoDTO.getStanza()));
        return posto;
    }
}
