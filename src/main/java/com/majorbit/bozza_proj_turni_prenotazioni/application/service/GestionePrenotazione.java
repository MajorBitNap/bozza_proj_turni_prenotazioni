package com.majorbit.bozza_proj_turni_prenotazioni.application.service;

import com.majorbit.bozza_proj_turni_prenotazioni.application.dto.PrenotazioneDTO;
import com.majorbit.bozza_proj_turni_prenotazioni.application.mapper.PostoMapper;
import com.majorbit.bozza_proj_turni_prenotazioni.application.mapper.PrenotazioneMapper;
import com.majorbit.bozza_proj_turni_prenotazioni.application.mapper.UtenteMapper;
import com.majorbit.bozza_proj_turni_prenotazioni.domain.model.Prenotazione;
import com.majorbit.bozza_proj_turni_prenotazioni.domain.repository.PrenotazioneRepository;
import com.majorbit.bozza_proj_turni_prenotazioni.util.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GestionePrenotazione {

    @Autowired
    private PrenotazioneRepository prenotazioneRepository;

    @Autowired
    private PrenotazioneMapper prenotazioneMapper;

    public PrenotazioneDTO createPrenotazione(PrenotazioneDTO prenotazioneDTO) {
        Prenotazione prenotazione = PrenotazioneMapper.toEntity(prenotazioneDTO);
        Prenotazione savedPrenotazione = prenotazioneRepository.save(prenotazione);
        return PrenotazioneMapper.toDTO(savedPrenotazione);
    }

    public PrenotazioneDTO getPrenotazioneById(Long id) {
        Prenotazione prenotazione = prenotazioneRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Prenotazione not found"));
        return PrenotazioneMapper.toDTO(prenotazione);
    }

    public List<PrenotazioneDTO> getAllPrenotazioni() {
        List<Prenotazione> prenotazioni = prenotazioneRepository.findAll();
        return prenotazioni.stream().map(PrenotazioneMapper::toDTO).collect(Collectors.toList());
    }

    public PrenotazioneDTO updatePrenotazione(Long id, PrenotazioneDTO prenotazioneDTO) {
        Prenotazione prenotazione = prenotazioneRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Prenotazione not found"));
        prenotazione.setDataInizio(prenotazioneDTO.getDataInizio());
        prenotazione.setDataFine(prenotazioneDTO.getDataFine());
        prenotazione.setStato(prenotazioneDTO.getStato());
        prenotazione.setPosto(PostoMapper.toEntity(prenotazioneDTO.getPosto()));
        prenotazione.setUtente(UtenteMapper.toEntity(prenotazioneDTO.getUtente()));;
        Prenotazione updatedPrenotazione = prenotazioneRepository.save(prenotazione);
        return PrenotazioneMapper.toDTO(updatedPrenotazione);
    }

    public void deletePrenotazione(Long id) {
        Prenotazione prenotazione = prenotazioneRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Prenotazione not found"));
        prenotazioneRepository.delete(prenotazione);
    }
}
