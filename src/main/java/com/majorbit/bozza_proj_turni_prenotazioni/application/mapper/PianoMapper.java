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
        return PianoDTO.builder()
                .nome(piano.getNome())
                .numero(piano.getNumero())
                .sede(piano.getSede().getId())
                .build();
    }

    public Piano toEntity(PianoDTO pianoDTO) {
        return Piano.builder()
                .nome(pianoDTO.getNome())
                .numero(pianoDTO.getNumero())
                .sede(sedeRepository.findById(pianoDTO.getSede()).orElseThrow())
                .build();
    }
}
