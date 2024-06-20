package com.majorbit.bozza_proj_turni_prenotazioni.application.service;

import com.majorbit.bozza_proj_turni_prenotazioni.application.dto.PianoDTO;
import com.majorbit.bozza_proj_turni_prenotazioni.application.mapper.PianoMapper;
import com.majorbit.bozza_proj_turni_prenotazioni.application.mapper.SedeMapper;
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

    private final PianoRepository pianoRepository;
    private final SedeRepository sedeRepository;
    private final PianoMapper pianoMapper;

    @Autowired
    public GestionePiano(PianoRepository pianoRepository,
                         SedeRepository sedeRepository,
                         PianoMapper pianoMapper) {
        this.pianoRepository = pianoRepository;
        this.sedeRepository = sedeRepository;
        this.pianoMapper = pianoMapper;
    }

    public PianoDTO createPiano(PianoDTO PianoDTO) {
        Piano piano = pianoMapper.toEntity(PianoDTO);
        Piano savedPiano = pianoRepository.save(piano);
        return pianoMapper.toDTO(savedPiano);
    }

    public PianoDTO getPianoById(Long id) {
        Piano piano = pianoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("resources not found"));
        return pianoMapper.toDTO(piano);
    }

    public List<PianoDTO> getAllPiani() {
        List<Piano> piani = pianoRepository.findAll();
        return piani.stream().map(pianoMapper::toDTO).collect(Collectors.toList());
    }

    public PianoDTO updatePiano(Long id, PianoDTO pianoDTO) {
        Piano piano = pianoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("resources not found"));
        piano.setNome(pianoDTO.getNome());
        piano.setNumero(pianoDTO.getNumero());
        piano.setSede(sedeRepository.findById(pianoDTO.getSede()).orElseThrow(() -> new ResourceNotFoundException("resources not found")));
        Piano updatedPiano = pianoRepository.save(piano);
        return pianoMapper.toDTO(updatedPiano);
    }

    public void deletePiano(Long id) {
        Piano piano = pianoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Piano not found"));
        pianoRepository.delete(piano);
    }
}
