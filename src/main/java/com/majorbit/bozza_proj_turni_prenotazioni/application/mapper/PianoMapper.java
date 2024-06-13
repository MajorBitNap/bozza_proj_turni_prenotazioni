package com.majorbit.bozza_proj_turni_prenotazioni.application.mapper;

import com.majorbit.bozza_proj_turni_prenotazioni.application.dto.PianoDTO;
import com.majorbit.bozza_proj_turni_prenotazioni.domain.model.Piano;
import org.springframework.stereotype.Component;

@Component
public class PianoMapper {

    public static PianoDTO toDTO(Piano piano) {
        return new PianoDTO(
                piano.getId(),
                piano.getNome(),
                piano.getNumero(),
                piano.getSede()
        );
    }

    public static Piano toEntity(PianoDTO pianoDTO) {
        Piano piano = new Piano();
        piano.setId(pianoDTO.id());
        piano.setNome(pianoDTO.nome());
        piano.setNumero(pianoDTO.numero());
        piano.setSede(pianoDTO.sede());
        return piano;
    }
}
