package com.majorbit.bozza_proj_turni_prenotazioni.application.service;

import com.majorbit.bozza_proj_turni_prenotazioni.application.dto.PrenotazioneDTO;
import com.majorbit.bozza_proj_turni_prenotazioni.application.mapper.PostoMapper;
import com.majorbit.bozza_proj_turni_prenotazioni.application.mapper.PrenotazioneMapper;
import com.majorbit.bozza_proj_turni_prenotazioni.application.mapper.UtenteMapper;
import com.majorbit.bozza_proj_turni_prenotazioni.domain.model.Prenotazione;
import com.majorbit.bozza_proj_turni_prenotazioni.domain.repository.PostoRepository;
import com.majorbit.bozza_proj_turni_prenotazioni.domain.repository.PrenotazioneRepository;
import com.majorbit.bozza_proj_turni_prenotazioni.domain.repository.UtenteRepository;
import com.majorbit.bozza_proj_turni_prenotazioni.util.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GestionePrenotazione {

    private final PrenotazioneRepository prenotazioneRepository;

    private final PostoRepository postoRepository;

    private final UtenteRepository utenteRepository;

    @Autowired
    public GestionePrenotazione(PrenotazioneRepository prenotazioneRepository,
                                PostoRepository postoRepository,
                                UtenteRepository utenteRepository) {
        this.prenotazioneRepository = prenotazioneRepository;
        this.postoRepository = postoRepository;
        this.utenteRepository = utenteRepository;
    }

    public PrenotazioneDTO createPrenotazione(PrenotazioneDTO PrenotazioneDTO) {
        Prenotazione prenotazione = PrenotazioneMapper.toEntity(PrenotazioneDTO);
        Prenotazione savedPrenotazione = prenotazioneRepository.save(prenotazione);
        return PrenotazioneMapper.toDTO(savedPrenotazione);
    }

    public PrenotazioneDTO getPrenotazioneById(Long id) {
        Prenotazione prenotazione = prenotazioneRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("resource not found"));
        return PrenotazioneMapper.toDTO(prenotazione);
    }

    public List<PrenotazioneDTO> getAllPrenotazioni() {
        List<Prenotazione> prenotazioni = prenotazioneRepository.findAll();
        return prenotazioni.stream().map(PrenotazioneMapper::toDTO).collect(Collectors.toList());
    }

    public PrenotazioneDTO updatePrenotazione(Long id, PrenotazioneDTO prenotazioneDTO) {
        Prenotazione prenotazione = prenotazioneRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("resource not found"));
        prenotazione.setDataInizio(prenotazioneDTO.getDataInizio());
        prenotazione.setDataFine(prenotazioneDTO.getDataFine());
        prenotazione.setStato(prenotazioneDTO.getStato());
        prenotazione.setPosto(postoRepository.findById(prenotazioneDTO.getPosto()).orElseThrow(() -> new ResourceNotFoundException("resource not found")));
        prenotazione.setUtente(utenteRepository.findById(prenotazioneDTO.getUtente()).orElseThrow(() -> new ResourceNotFoundException("resource not found")));;
        Prenotazione updatedPrenotazione = prenotazioneRepository.save(prenotazione);
        return PrenotazioneMapper.toDTO(updatedPrenotazione);
    }

    public void deletePrenotazione(Long id) {
        Prenotazione prenotazione = prenotazioneRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("resource not found"));
        prenotazioneRepository.delete(prenotazione);
    }
}
