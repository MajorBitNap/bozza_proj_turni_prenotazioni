package com.majorbit.bozza_proj_turni_prenotazioni.application.service;

import com.majorbit.bozza_proj_turni_prenotazioni.application.dto.PrenotazioneDTO;
import com.majorbit.bozza_proj_turni_prenotazioni.application.mapper.PrenotazioneMapper;
import com.majorbit.bozza_proj_turni_prenotazioni.domain.model.Prenotazione;
import com.majorbit.bozza_proj_turni_prenotazioni.domain.repository.PostoRepository;
import com.majorbit.bozza_proj_turni_prenotazioni.domain.repository.PrenotazioneRepository;
import com.majorbit.bozza_proj_turni_prenotazioni.domain.repository.UtenteRepository;
import com.majorbit.bozza_proj_turni_prenotazioni.util.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GestionePrenotazione {

    private final PrenotazioneRepository prenotazioneRepository;
    private final PostoRepository postoRepository;
    private final UtenteRepository utenteRepository;
    private final PrenotazioneMapper prenotazioneMapper;

    public PrenotazioneDTO createPrenotazione(PrenotazioneDTO PrenotazioneDTO) {
        var prenotazione = prenotazioneMapper.toEntity(PrenotazioneDTO);
        var savedPrenotazione = prenotazioneRepository.save(prenotazione);
        return prenotazioneMapper.toDTO(savedPrenotazione);
    }

    public PrenotazioneDTO getPrenotazioneById(Integer id) {
        var prenotazione = prenotazioneRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Resource not found"));
        return prenotazioneMapper.toDTO(prenotazione);
    }

    public List<PrenotazioneDTO> getAllPrenotazioni() {
        List<Prenotazione> prenotazioni = prenotazioneRepository.findAll();
        return prenotazioni.stream().map(prenotazioneMapper::toDTO).collect(Collectors.toList());
    }

    public PrenotazioneDTO updatePrenotazione(Integer id, PrenotazioneDTO prenotazioneDTO) {
        var prenotazione = prenotazioneRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Resource not found"));
        prenotazione.setDataInizio(prenotazioneDTO.getDataInizio());
        prenotazione.setDataFine(prenotazioneDTO.getDataFine());
        prenotazione.setStato(prenotazioneDTO.getStato());
        prenotazione.setPosto(postoRepository.findById(prenotazioneDTO.getPosto()).orElseThrow(() -> new ResourceNotFoundException("Resource not found")));
        prenotazione.setUtente(utenteRepository.findById(prenotazioneDTO.getUtente()).orElseThrow(() -> new ResourceNotFoundException("Resource not found")));;
        var updatedPrenotazione = prenotazioneRepository.save(prenotazione);
        return prenotazioneMapper.toDTO(updatedPrenotazione);
    }

    public void deletePrenotazione(Integer id) {
        var prenotazione = prenotazioneRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Resource not found"));
        prenotazioneRepository.delete(prenotazione);
    }
}
