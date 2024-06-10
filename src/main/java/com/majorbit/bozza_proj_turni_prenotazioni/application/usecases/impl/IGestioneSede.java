package com.majorbit.bozza_proj_turni_prenotazioni.application.usecases.impl;

import com.majorbit.bozza_proj_turni_prenotazioni.application.dto.SedeDTO;
import com.majorbit.bozza_proj_turni_prenotazioni.application.mapper.SedeMapper;
import com.majorbit.bozza_proj_turni_prenotazioni.application.usecases.spec.GestioneSede;
import com.majorbit.bozza_proj_turni_prenotazioni.domain.model.Sede;
import com.majorbit.bozza_proj_turni_prenotazioni.domain.repository.SedeRepository;
import com.majorbit.bozza_proj_turni_prenotazioni.util.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class IGestioneSede implements GestioneSede {

    @Autowired
    private SedeRepository sedeRepository;

    @Autowired
    private SedeMapper sedeMapper;

    @Override
    public SedeDTO createSede(SedeDTO sedeDTO) {
        Sede sede = SedeMapper.toEntity(sedeDTO);
        Sede savedSede = sedeRepository.save(sede);
        return SedeMapper.toDTO(savedSede);
    }

    @Override
    public SedeDTO getSedeById(Long id) {
        Sede sede = sedeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Sede not found"));
        return SedeMapper.toDTO(sede);
    }

    @Override
    public List<SedeDTO> getAllSedi() {
        List<Sede> sedi = sedeRepository.findAll();
        return sedi.stream().map(SedeMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public SedeDTO updateSede(Long id, SedeDTO sedeDTO) {
        Sede sede = sedeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Sede not found"));
        sede.setId(sedeDTO.id());
        sede.setNome(sedeDTO.nome());
        sede.setIndirizzo(sedeDTO.indirizzo());
        Sede updatedSede = sedeRepository.save(sede);
        return SedeMapper.toDTO(updatedSede);
    }

    @Override
    public void deleteSede(Long id) {
        Sede sede = sedeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Sede not found"));
        sedeRepository.delete(sede);
    }
}
