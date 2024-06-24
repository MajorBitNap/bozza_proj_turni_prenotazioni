package com.majorbit.bozza_proj_turni_prenotazioni.application.mapper;

import com.majorbit.bozza_proj_turni_prenotazioni.application.dto.PianoDTO;
import com.majorbit.bozza_proj_turni_prenotazioni.domain.model.Piano;
import com.majorbit.bozza_proj_turni_prenotazioni.domain.repository.SedeRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PianoMapper {

    private final SedeRepository sedeRepository;

    public PianoDTO toDTO(Piano piano) {
        return new PianoDTO(
                piano.getNome(),
                piano.getNumero(),
                piano.getSede().getId()
        );
    }

    public Piano toEntity(PianoDTO pianoDTO) {
        var piano = new Piano();
        piano.setNome(pianoDTO.getNome());
        piano.setNumero(pianoDTO.getNumero());
        piano.setSede(sedeRepository.findById(pianoDTO.getSede()).orElseThrow());
        return piano;
    }
}
