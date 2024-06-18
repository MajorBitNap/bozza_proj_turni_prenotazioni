package com.majorbit.bozza_proj_turni_prenotazioni.application.usecases.impl;

import com.majorbit.bozza_proj_turni_prenotazioni.application.dto.UtenteDTO;
import com.majorbit.bozza_proj_turni_prenotazioni.application.mapper.UtenteMapper;
import com.majorbit.bozza_proj_turni_prenotazioni.application.usecases.spec.GestioneUtente;
import com.majorbit.bozza_proj_turni_prenotazioni.domain.model.Utente;
import com.majorbit.bozza_proj_turni_prenotazioni.domain.repository.UtenteRepository;
import com.majorbit.bozza_proj_turni_prenotazioni.util.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class IGestioneUtente implements GestioneUtente {

    @Autowired
    private UtenteRepository utenteRepository;

    @Autowired
    private UtenteMapper utenteMapper;

    @Override
        public UtenteDTO createUtente(UtenteDTO utenteDTO) {
        Utente utente = UtenteMapper.toEntity(utenteDTO);
        Utente savedUtente = utenteRepository.save(utente);
        return UtenteMapper.toDTO(savedUtente);
    }

    @Override
    public UtenteDTO getUtenteById(Long id) {
        Utente utente = utenteRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Utente not found"));
        return UtenteMapper.toDTO(utente);
    }

    @Override
    public List<UtenteDTO> getAllUtenti() {
        List<Utente> utenti = utenteRepository.findAll();
        return utenti.stream().map(UtenteMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public UtenteDTO updateUtente(Long id, UtenteDTO utenteDTO) {
        Utente utente = utenteRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Utente not found"));
        utente.setId(utenteDTO.getId());
        utente.setNome(utenteDTO.getNome());
        utente.setCognome(utenteDTO.getCognome());
        utente.setEmail(utenteDTO.getEmail());
        utente.setRuolo(utenteDTO.getRuolo());
        Utente updatedUtente = utenteRepository.save(utente);
        return UtenteMapper.toDTO(updatedUtente);
    }

    @Override
    public void deleteUtente(Long id) {
        Utente utente = utenteRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Utente not found"));
        utenteRepository.delete(utente);
    }
}
