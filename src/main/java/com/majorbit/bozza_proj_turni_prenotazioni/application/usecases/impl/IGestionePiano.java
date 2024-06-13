package com.majorbit.bozza_proj_turni_prenotazioni.application.usecases.impl;

import com.majorbit.bozza_proj_turni_prenotazioni.application.dto.PianoDTO;
import com.majorbit.bozza_proj_turni_prenotazioni.application.mapper.PianoMapper;
import com.majorbit.bozza_proj_turni_prenotazioni.application.usecases.spec.GestionePiano;
import com.majorbit.bozza_proj_turni_prenotazioni.domain.model.Piano;
import com.majorbit.bozza_proj_turni_prenotazioni.domain.repository.PianoRepository;
import com.majorbit.bozza_proj_turni_prenotazioni.domain.repository.SedeRepository;
import com.majorbit.bozza_proj_turni_prenotazioni.infrastructure.repository.JPASedeRepository;
import com.majorbit.bozza_proj_turni_prenotazioni.util.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class IGestionePiano implements GestionePiano {

    @Autowired
    private PianoRepository pianoRepository;

    @Autowired
    private SedeRepository sedeRepository;

    @Autowired
    private PianoMapper pianoMapper;

    @Override
    public PianoDTO createPiano(PianoDTO pianoDTO) {
        Piano piano = PianoMapper.toEntity(pianoDTO);
        Piano savedPiano = pianoRepository.save(piano);
        return PianoMapper.toDTO(savedPiano);
    }

    @Override
    public PianoDTO getPianoById(Long id) {
        Piano piano = pianoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Piano not found"));
        return PianoMapper.toDTO(piano);
    }

    @Override
    public List<PianoDTO> getAllPiani() {
        List<Piano> piani = pianoRepository.findAll();
        return piani.stream().map(PianoMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public PianoDTO updatePiano(Long id, PianoDTO pianoDTO) {
        Piano piano = pianoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Piano not found"));
        piano.setId(pianoDTO.id());
        piano.setNome(pianoDTO.nome());
        piano.setNumero(pianoDTO.numero());
        piano.setSede(sedeRepository.findSedeById(pianoDTO.sede().getId()));
        Piano updatedPiano = pianoRepository.save(piano);
        return PianoMapper.toDTO(updatedPiano);
    }

    @Override
    public void deletePiano(Long id) {
        Piano piano = pianoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Piano not found"));
        pianoRepository.delete(piano);
    }
}
