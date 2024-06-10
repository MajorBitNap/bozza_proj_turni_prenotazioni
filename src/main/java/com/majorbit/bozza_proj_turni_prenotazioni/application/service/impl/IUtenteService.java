package com.majorbit.bozza_proj_turni_prenotazioni.application.service.impl;

import com.majorbit.bozza_proj_turni_prenotazioni.application.dto.UtenteDTO;
import com.majorbit.bozza_proj_turni_prenotazioni.application.mapper.UtenteMapper;
import com.majorbit.bozza_proj_turni_prenotazioni.application.service.spec.UtenteService;
import com.majorbit.bozza_proj_turni_prenotazioni.domain.model.Utente;
import com.majorbit.bozza_proj_turni_prenotazioni.domain.repository.UtenteRepository;
import com.majorbit.bozza_proj_turni_prenotazioni.util.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class IUtenteService implements UtenteService {

    @Autowired
    private UtenteRepository utenteRepository;

    @Autowired
    private UtenteMapper utenteMapper;

    @Override
    public UtenteDTO createUtente(UtenteDTO utenteDTO) {
        Utente utente = utenteMapper.toEntity(utenteDTO);
        Utente savedUtente = utenteRepository.save(utente);
        return utenteMapper.toDTO(savedUtente);
    }

    @Override
    public UtenteDTO getUtenteById(Long id) {
        Utente utente = utenteRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Utente not found"));
        return utenteMapper.toDTO(utente);
    }

    @Override
    public List<UtenteDTO> getAllUtenti() {
        List<Utente> utenti = utenteRepository.findAll();
        return utenti.stream().map(utenteMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public UtenteDTO updateUtente(Long id, UtenteDTO utenteDTO) {
        Utente utente = utenteRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Utente not found"));
        utente.setId(utenteDTO.id());
        utente.setNome(utenteDTO.nome());
        utente.setCognome(utenteDTO.cognome());
        utente.setEmail(utenteDTO.email());
        utente.setRuolo(utenteDTO.ruolo());
        Utente updatedUtente = utenteRepository.save(utente);
        return utenteMapper.toDTO(updatedUtente);
    }

    @Override
    public void deleteUtente(Long id) {
        Utente utente = utenteRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Utente not found"));
        utenteRepository.delete(utente);
    }
}
