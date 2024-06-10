package com.majorbit.bozza_proj_turni_prenotazioni.application.service.impl;

import com.majorbit.bozza_proj_turni_prenotazioni.application.dto.PrenotazioneDTO;
import com.majorbit.bozza_proj_turni_prenotazioni.application.mapper.PrenotazioneMapper;
import com.majorbit.bozza_proj_turni_prenotazioni.application.service.spec.PrenotazioneService;
import com.majorbit.bozza_proj_turni_prenotazioni.domain.model.Prenotazione;
import com.majorbit.bozza_proj_turni_prenotazioni.domain.repository.PrenotazioneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IPrenotazioneService implements PrenotazioneService {

    @Autowired
    private PrenotazioneRepository prenotazioneRepository;

    @Autowired
    private PrenotazioneMapper prenotazioneMapper;

    @Override
    public PrenotazioneDTO createPrenotazione(PrenotazioneDTO prenotazioneDTO) {
        Prenotazione prenotazione = prenotazioneMapper.toEntity(prenotazioneDTO);
        Prenotazione savedPrenotazione = prenotazioneRepository.save(prenotazione);
        return prenotazioneMapper.toDTO(savedPrenotazione);
    }

    @Override
    public PrenotazioneDTO getPrenotazioneById(Long id) {
        Prenotazione prenotazione = prenotazioneRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Prenotazione not found"));
        return prenotazioneMapper.toDTO(prenotazione);
    }

    @Override
    public List<PrenotazioneDTO> getAllPrenotazioni() {
        List<Prenotazione> prenotazioni = prenotazioneRepository.findAll();
        return prenotazioni.stream().map(prenotazioneMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public PrenotazioneDTO updatePrenotazione(Long id, PrenotazioneDTO prenotazioneDTO) {
        Prenotazione prenotazione = prenotazioneRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Prenotazione not found"));
        prenotazione.setDataInizio(prenotazioneDTO.getDataInizio());
        prenotazione.setDataFine(prenotazioneDTO.getDataFine());
        prenotazione.setStato(prenotazioneDTO.getStato());
        prenotazione.setPosto(prenotazioneMapper.toEntity(prenotazioneDTO.getPosto()));
        prenotazione.setUtente(prenotazioneMapper.toEntity(prenotazioneDTO.getUtente()));
        Prenotazione updatedPrenotazione = prenotazioneRepository.save(prenotazione);
        return prenotazioneMapper.toDTO(updatedPrenotazione);
    }

    @Override
    public void deletePrenotazione(Long id) {
        Prenotazione prenotazione = prenotazioneRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Prenotazione not found"));
        prenotazioneRepository.delete(prenotazione);
    }
}
