package com.majorbit.bozza_proj_turni_prenotazioni.application.mapper;

import com.majorbit.bozza_proj_turni_prenotazioni.application.dto.PianoDTO;
import com.majorbit.bozza_proj_turni_prenotazioni.domain.model.Piano;

public class PianoMapper {

    public static PianoDTO toDTO(Piano piano) {
        return new PianoDTO(
                piano.getId(),
                piano.getNome(),
                piano.getNumero(),
                piano.getSedeId()
        );
    }

    public static Piano toEntity(PianoDTO pianoDTO) {
        Piano piano = new Piano();
        piano.setId(pianoDTO.id());
        piano.setNome(pianoDTO.nome());
        piano.setNumero(pianoDTO.numero());
        piano.setSedeId(pianoDTO.sedeId());
        return piano;
    }
}