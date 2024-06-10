package com.majorbit.bozza_proj_turni_prenotazioni.application.service.impl;

import com.majorbit.bozza_proj_turni_prenotazioni.application.dto.SedeDTO;
import com.majorbit.bozza_proj_turni_prenotazioni.application.mapper.SedeMapper;
import com.majorbit.bozza_proj_turni_prenotazioni.application.service.spec.SedeService;
import com.majorbit.bozza_proj_turni_prenotazioni.domain.model.Sede;
import com.majorbit.bozza_proj_turni_prenotazioni.domain.repository.SedeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ISedeService implements SedeService {

    @Autowired
    private SedeRepository sedeRepository;

    @Autowired
    private SedeMapper sedeMapper;

    @Override
    public SedeDTO createSede(SedeDTO sedeDTO) {
        Sede sede = sedeMapper.toEntity(sedeDTO);
        Sede savedSede = sedeRepository.save(sede);
        return sedeMapper.toDTO(savedSede);
    }

    @Override
    public SedeDTO getSedeById(Long id) {
        Sede sede = sedeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Sede not found"));
        return sedeMapper.toDTO(sede);
    }

    @Override
    public List<SedeDTO> getAllSedi() {
        List<Sede> sedi = sedeRepository.findAll();
        return sedi.stream().map(sedeMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public SedeDTO updateSede(Long id, SedeDTO sedeDTO) {
        Sede sede = sedeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Sede not found"));
        sede.setNome(sedeDTO.getNome());
        sede.setIndirizzo(sedeDTO.getIndirizzo());
        Sede updatedSede = sedeRepository.save(sede);
        return sedeMapper.toDTO(updatedSede);
    }

    @Override
    public void deleteSede(Long id) {
        Sede sede = sedeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Sede not found"));
        sedeRepository.delete(sede);
    }
}
