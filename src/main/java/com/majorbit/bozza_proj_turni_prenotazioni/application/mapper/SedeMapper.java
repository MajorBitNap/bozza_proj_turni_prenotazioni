package com.majorbit.bozza_proj_turni_prenotazioni.application.mapper;

import com.majorbit.bozza_proj_turni_prenotazioni.application.dto.SedeDTO;
import com.majorbit.bozza_proj_turni_prenotazioni.domain.model.Sede;
import org.springframework.stereotype.Component;

@Component
public class SedeMapper {

    public static SedeDTO toDTO(Sede sede) {
        return new SedeDTO(sede.getId(), sede.getNome(), sede.getIndirizzo());
    }

    public static Sede toEntity(SedeDTO sedeDTO) {
        Sede sede = new Sede();
        sede.setId(sedeDTO.getId());
        sede.setNome(sedeDTO.getNome());
        sede.setIndirizzo(sedeDTO.getIndirizzo());
        return sede;
    }
}
