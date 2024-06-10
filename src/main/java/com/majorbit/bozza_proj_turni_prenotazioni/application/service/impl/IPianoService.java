package com.majorbit.bozza_proj_turni_prenotazioni.application.service.impl;

import com.majorbit.bozza_proj_turni_prenotazioni.application.dto.PianoDTO;
import com.majorbit.bozza_proj_turni_prenotazioni.application.mapper.PianoMapper;
import com.majorbit.bozza_proj_turni_prenotazioni.application.service.spec.PianoService;
import com.majorbit.bozza_proj_turni_prenotazioni.domain.model.Piano;
import com.majorbit.bozza_proj_turni_prenotazioni.domain.repository.PianoRepository;
import com.majorbit.bozza_proj_turni_prenotazioni.util.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class IPianoService implements PianoService {

    @Autowired
    private PianoRepository pianoRepository;

    @Autowired
    private PianoMapper pianoMapper;

    @Override
    public PianoDTO createPiano(PianoDTO pianoDTO) {
        Piano piano = pianoMapper.toEntity(pianoDTO);
        Piano savedPiano = pianoRepository.save(piano);
        return pianoMapper.toDTO(savedPiano);
    }

    @Override
    public PianoDTO getPianoById(Long id) {
        Piano piano = pianoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Piano not found"));
        return pianoMapper.toDTO(piano);
    }

    @Override
    public List<PianoDTO> getAllPiani() {
        List<Piano> piani = pianoRepository.findAll();
        return piani.stream().map(pianoMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public PianoDTO updatePiano(Long id, PianoDTO pianoDTO) {
        Piano piano = pianoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Piano not found"));
        piano.setId(pianoDTO.id());
        piano.setNome(pianoDTO.nome());
        piano.setNumero(pianoDTO.numero());
        piano.setSedeId(pianoDTO.sedeId());
        Piano updatedPiano = pianoRepository.save(piano);
        return pianoMapper.toDTO(updatedPiano);
    }

    @Override
    public void deletePiano(Long id) {
        Piano piano = pianoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Piano not found"));
        pianoRepository.delete(piano);
    }
}
