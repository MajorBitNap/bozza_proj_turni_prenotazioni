package com.majorbit.bozza_proj_turni_prenotazioni.application.service;

import com.majorbit.bozza_proj_turni_prenotazioni.application.dto.PianoDTO;
import com.majorbit.bozza_proj_turni_prenotazioni.application.mapper.PianoMapper;
import com.majorbit.bozza_proj_turni_prenotazioni.domain.model.Piano;
import com.majorbit.bozza_proj_turni_prenotazioni.domain.repository.PianoRepository;
import com.majorbit.bozza_proj_turni_prenotazioni.domain.repository.SedeRepository;
import com.majorbit.bozza_proj_turni_prenotazioni.util.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GestionePiano {

    @Autowired
    private PianoRepository pianoRepository;

    @Autowired
    private SedeRepository sedeRepository;

    @Autowired
    private PianoMapper pianoMapper;

    public PianoDTO createPiano(PianoDTO pianoDTO) {
        Piano piano = PianoMapper.toEntity(pianoDTO);
        Piano savedPiano = pianoRepository.save(piano);
        return PianoMapper.toDTO(savedPiano);
    }

    public PianoDTO getPianoById(Long id) {
        Piano piano = pianoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Piano not found"));
        return PianoMapper.toDTO(piano);
    }

    public List<PianoDTO> getAllPiani() {
        List<Piano> piani = pianoRepository.findAll();
        return piani.stream().map(PianoMapper::toDTO).collect(Collectors.toList());
    }

    public PianoDTO updatePiano(Long id, PianoDTO pianoDTO) {
        Piano piano = pianoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Piano not found"));
        piano.setId(pianoDTO.getId());
        piano.setNome(pianoDTO.getNome());
        piano.setNumero(pianoDTO.getNumero());
        piano.setSede(sedeRepository.findSedeById(pianoDTO.getSede().getId()));
        Piano updatedPiano = pianoRepository.save(piano);
        return PianoMapper.toDTO(updatedPiano);
    }

    public void deletePiano(Long id) {
        Piano piano = pianoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Piano not found"));
        pianoRepository.delete(piano);
    }
}
