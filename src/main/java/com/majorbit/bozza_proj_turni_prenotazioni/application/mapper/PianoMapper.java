package com.majorbit.bozza_proj_turni_prenotazioni.application.mapper;

import com.majorbit.bozza_proj_turni_prenotazioni.application.dto.PianoDTO;
import com.majorbit.bozza_proj_turni_prenotazioni.domain.model.Piano;
import com.majorbit.bozza_proj_turni_prenotazioni.domain.repository.SedeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PianoMapper {

    private static SedeRepository sedeRepository;

    @Autowired
    public PianoMapper(SedeRepository sedeRepository) {
        this.sedeRepository = sedeRepository;
    }

    public static PianoDTO toDTO(Piano piano) {
        return new PianoDTO(
                piano.getNome(),
                piano.getNumero(),
                piano.getSede().getId()
        );
    }

    public static Piano toEntity(PianoDTO pianoDTO) {
        Piano piano = new Piano();
        piano.setNome(pianoDTO.getNome());
        piano.setNumero(pianoDTO.getNumero());
        piano.setSede(sedeRepository.findById(pianoDTO.getSede()).orElseThrow());
        return piano;
    }
}
