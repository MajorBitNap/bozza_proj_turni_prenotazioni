package com.majorbit.bozza_proj_turni_prenotazioni.application.mapper;

import com.majorbit.bozza_proj_turni_prenotazioni.application.dto.PostoDTO;
import com.majorbit.bozza_proj_turni_prenotazioni.domain.model.Posto;
import org.springframework.stereotype.Component;

@Component
public class PostoMapper {

    public static PostoDTO toDTO(Posto posto) {
        return new com.majorbit.bozza_proj_turni_prenotazioni.application.dto.PostoDTO(
            posto.getNome(),
            posto.isDisponibile(),
            posto.getStanza()
        );
    }

    public static Posto toEntity(PostoDTO postoDTO) {
        Posto posto = new Posto();
        posto.setDisponibile(postoDTO.isDisponibile());
        posto.setNome(postoDTO.getNome());
        posto.setStanza(postoDTO.getStanza());
        return posto;
    }
}
