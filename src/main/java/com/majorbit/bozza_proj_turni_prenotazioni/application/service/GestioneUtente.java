package com.majorbit.bozza_proj_turni_prenotazioni.application.service;

import com.majorbit.bozza_proj_turni_prenotazioni.application.dto.UtenteDTO;
import com.majorbit.bozza_proj_turni_prenotazioni.application.mapper.UtenteMapper;
import com.majorbit.bozza_proj_turni_prenotazioni.domain.model.Utente;
import com.majorbit.bozza_proj_turni_prenotazioni.domain.repository.UtenteRepository;
import com.majorbit.bozza_proj_turni_prenotazioni.util.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GestioneUtente {

    private final UtenteRepository utenteRepository;

    @Autowired
    public GestioneUtente(UtenteRepository utenteRepository) {
        this.utenteRepository = utenteRepository;
    }

    public UtenteDTO createUtente(UtenteDTO UtenteDTO) {
        Utente utente = UtenteMapper.toEntity(UtenteDTO);
        Utente savedUtente = utenteRepository.save(utente);
        return UtenteMapper.toDTO(savedUtente);
    }

    public UtenteDTO getUtenteById(Long id) {
        Utente utente = utenteRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Utente not found"));
        return UtenteMapper.toDTO(utente);
    }

    public List<UtenteDTO> getAllUtenti() {
        List<Utente> utenti = utenteRepository.findAll();
        return utenti.stream().map(UtenteMapper::toDTO).collect(Collectors.toList());
    }

    public UtenteDTO updateUtente(Long id, UtenteDTO UtenteDTO) {
        Utente utente = utenteRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Utente not found"));
        utente.setNome(UtenteDTO.getNome());
        utente.setCognome(UtenteDTO.getCognome());
        utente.setEmail(UtenteDTO.getEmail());
        utente.setRuolo(UtenteDTO.getRuolo());
        Utente updatedUtente = utenteRepository.save(utente);
        return UtenteMapper.toDTO(updatedUtente);
    }

    public void deleteUtente(Long id) {
        Utente utente = utenteRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Utente not found"));
        utenteRepository.delete(utente);
    }
}
