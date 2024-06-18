package com.majorbit.bozza_proj_turni_prenotazioni.application.mapper;

import com.majorbit.bozza_proj_turni_prenotazioni.application.dto.PianoDTO;
import com.majorbit.bozza_proj_turni_prenotazioni.domain.model.Piano;
import org.springframework.stereotype.Component;

@Component
public class PianoMapper {

    public static PianoDTO toDTO(Piano piano) {
        return new PianoDTO(
                piano.getNome(),
                piano.getNumero(),
                piano.getSede()
        );
    }

    public static Piano toEntity(PianoDTO pianoDTO) {
        Piano piano = new Piano();
        piano.setNome(pianoDTO.getNome());
        piano.setNumero(pianoDTO.getNumero());
        piano.setSede(pianoDTO.getSede());
        return piano;
    }
}
