package com.majorbit.bozza_proj_turni_prenotazioni.application.service;

import com.majorbit.bozza_proj_turni_prenotazioni.application.dto.PianoDTO;
import com.majorbit.bozza_proj_turni_prenotazioni.application.mapper.PianoMapper;
import com.majorbit.bozza_proj_turni_prenotazioni.domain.model.Piano;
import com.majorbit.bozza_proj_turni_prenotazioni.domain.repository.PianoRepository;
import com.majorbit.bozza_proj_turni_prenotazioni.domain.repository.SedeRepository;
import com.majorbit.bozza_proj_turni_prenotazioni.util.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GestionePiano {

    private final PianoRepository pianoRepository;
    private final SedeRepository sedeRepository;
    private final PianoMapper pianoMapper;

    public PianoDTO createPiano(PianoDTO pianoDTO) {
        var piano = pianoMapper.toEntity(pianoDTO);
        var savedPiano = pianoRepository.save(piano);
        return pianoMapper.toDTO(savedPiano);
    }

    public PianoDTO getPianoById(Integer id) {
        var piano = pianoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("resources not found"));
        return pianoMapper.toDTO(piano);
    }

    public List<PianoDTO> getAllPiani() {
        List<Piano> piani = pianoRepository.findAll();
        return piani.stream().map(pianoMapper::toDTO).collect(Collectors.toList());
    }

    public PianoDTO updatePiano(Integer id, PianoDTO pianoDTO) {
        var piano = pianoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("resources not found"));
        piano.setNome(pianoDTO.getNome());
        piano.setNumero(pianoDTO.getNumero());
        piano.setSede(sedeRepository.findById(pianoDTO.getSede()).orElseThrow(() -> new ResourceNotFoundException("resources not found")));
        var updatedPiano = pianoRepository.save(piano);
        return pianoMapper.toDTO(updatedPiano);
    }

    public void deletePiano(Integer id) {
        var piano = pianoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Resource not found"));
        pianoRepository.delete(piano);
    }
}
