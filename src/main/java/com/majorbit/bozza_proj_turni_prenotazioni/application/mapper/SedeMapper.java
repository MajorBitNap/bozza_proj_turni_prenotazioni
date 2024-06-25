package com.majorbit.bozza_proj_turni_prenotazioni.application.mapper;

import com.majorbit.bozza_proj_turni_prenotazioni.application.dto.SedeDTO;
import com.majorbit.bozza_proj_turni_prenotazioni.domain.model.Sede;
import org.springframework.stereotype.Component;

@Component
public class SedeMapper {

    public SedeDTO toDTO(Sede sede) {
        return SedeDTO.builder()
                .nome(sede.getNome())
                .indirizzo(sede.getIndirizzo())
                .build();
    }

    public Sede toEntity(SedeDTO sedeDTO) {
        return Sede.builder()
                .nome(sedeDTO.getNome())
                .indirizzo(sedeDTO.getIndirizzo())
                .build();
    }
}
