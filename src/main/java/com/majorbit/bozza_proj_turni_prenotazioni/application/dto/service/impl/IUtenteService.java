package com.majorbit.bozza_proj_turni_prenotazioni.application.dto.service.impl;

import com.majorbit.bozza_proj_turni_prenotazioni.application.dto.UtenteDTO;
import com.majorbit.bozza_proj_turni_prenotazioni.application.dto.service.spec.UtenteService;
import com.majorbit.bozza_proj_turni_prenotazioni.domain.repository.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IUtenteService implements UtenteService {

    @Autowired
    private UtenteRepository utenteRepository;

    @Autowired
    private UtenteMapper utenteMapper;

    @Override
    public UtenteDTO createUtente(UtenteDTO utenteDTO) {
        Utente utente = utenteMapper.toUtente(utenteDTO);
        Utente savedUtente = utenteRepository.save(utente);
        return utenteMapper.toUtenteDTO(savedUtente);
    }

    @Override
    public UtenteDTO getUtenteById(Long id) {
        Utente utente = utenteRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Utente not found"));
        return utenteMapper.toUtenteDTO(utente);
    }

    @Override
    public List<UtenteDTO> getAllUtenti() {
        List<Utente> utenti = utenteRepository.findAll();
        return utenti.stream().map(utenteMapper::toUtenteDTO).collect(Collectors.toList());
    }

    @Override
    public UtenteDTO updateUtente(Long id, UtenteDTO utenteDTO) {
        Utente utente = utenteRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Utente not found"));
        utente.setNome(utenteDTO.getNome());
        utente.setCognome(utenteDTO.getCognome());
        utente.setEmail(utenteDTO.getEmail());
        utente.setPassword(utenteDTO.getPassword());
        utente.setRuolo(utenteDTO.getRuolo());
        Utente updatedUtente = utenteRepository.save(utente);
        return utenteMapper.toUtenteDTO(updatedUtente);
    }

    @Override
    public void deleteUtente(Long id) {
        Utente utente = utenteRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Utente not found"));
        utenteRepository.delete(utente);
    }
}
