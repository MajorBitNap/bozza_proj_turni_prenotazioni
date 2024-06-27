package com.majorbit.bozza_proj_turni_prenotazioni.application.service;

import com.majorbit.bozza_proj_turni_prenotazioni.application.dto.UtenteDTO;
import com.majorbit.bozza_proj_turni_prenotazioni.application.mapper.UtenteMapper;
import com.majorbit.bozza_proj_turni_prenotazioni.domain.model.Utente;
import com.majorbit.bozza_proj_turni_prenotazioni.domain.repository.UtenteRepository;
import com.majorbit.bozza_proj_turni_prenotazioni.util.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GestioneUtente {

    private final UtenteRepository utenteRepository;
    private final UtenteMapper utenteMapper;

    public UtenteDTO createUtente(UtenteDTO utenteDTO) {
        var utente = utenteMapper.toEntity(utenteDTO);
        var savedUtente = utenteRepository.save(utente);
        return utenteMapper.toDTO(savedUtente);
    }

    public UtenteDTO getUtenteById(Integer id) {
        var utente = utenteRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Resource not found"));
        return utenteMapper.toDTO(utente);
    }

    public List<UtenteDTO> getAllUtenti() {
        List<Utente> utenti = utenteRepository.findAll();
        return utenti.stream().map(utenteMapper::toDTO).collect(Collectors.toList());
    }

    public UtenteDTO updateUtente(Integer id, UtenteDTO utenteDTO) {
        var utente = utenteRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Resource not found"));
        utente.setNome(utenteDTO.getNome());
        utente.setCognome(utenteDTO.getCognome());
        utente.setEmail(utenteDTO.getEmail());
        utente.setRuolo(utenteDTO.getRuolo());
        var updatedUtente = utenteRepository.save(utente);
        return utenteMapper.toDTO(updatedUtente);
    }

    public void deleteUtente(Integer id) {
        var utente = utenteRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Resource not found"));
        utenteRepository.delete(utente);
    }
}
