package com.majorbit.bozza_proj_turni_prenotazioni.application.service;

import com.majorbit.bozza_proj_turni_prenotazioni.application.dto.SedeDTO;
import com.majorbit.bozza_proj_turni_prenotazioni.application.mapper.SedeMapper;
import com.majorbit.bozza_proj_turni_prenotazioni.domain.model.Sede;
import com.majorbit.bozza_proj_turni_prenotazioni.domain.repository.SedeRepository;
import com.majorbit.bozza_proj_turni_prenotazioni.util.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GestioneSede {

    private final SedeRepository sedeRepository;
    private final SedeMapper sedeMapper;

    public SedeDTO createSede(SedeDTO SedeDTO) {
        Sede sede = sedeMapper.toEntity(SedeDTO);
        Sede savedSede = sedeRepository.save(sede);
        return sedeMapper.toDTO(savedSede);
    }

    public SedeDTO getSedeById(Integer id) {
        Sede sede = sedeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Sede not found"));
        return sedeMapper.toDTO(sede);
    }

    public List<SedeDTO> getAllSedi() {
        List<Sede> sedi = sedeRepository.findAll();
        return sedi.stream().map(sedeMapper::toDTO).collect(Collectors.toList());
    }

    public SedeDTO updateSede(Integer id, SedeDTO SedeDTO) {
        Sede sede = sedeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Sede not found"));
        sede.setNome(SedeDTO.getNome());
        sede.setIndirizzo(SedeDTO.getIndirizzo());
        Sede updatedSede = sedeRepository.save(sede);
        return sedeMapper.toDTO(updatedSede);
    }

    public void deleteSede(Integer id) {
        Sede sede = sedeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Sede not found"));
        sedeRepository.delete(sede);
    }
}
