package com.majorbit.bozza_proj_turni_prenotazioni.application.mapper;

import com.majorbit.bozza_proj_turni_prenotazioni.application.dto.SedeDTO;
import com.majorbit.bozza_proj_turni_prenotazioni.domain.model.Sede;
import org.springframework.stereotype.Component;

@Component
public class SedeMapper {

    public SedeDTO toDTO(Sede sede) {
        return new SedeDTO(sede.getNome(), sede.getIndirizzo());
    }

    public Sede toEntity(SedeDTO sedeDTO) {
        var sede = new Sede();
        sede.setNome(sedeDTO.getNome());
        sede.setIndirizzo(sedeDTO.getIndirizzo());
        return sede;
    }
}
