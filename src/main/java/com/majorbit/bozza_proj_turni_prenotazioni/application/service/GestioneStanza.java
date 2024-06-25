package com.majorbit.bozza_proj_turni_prenotazioni.application.service;

import com.majorbit.bozza_proj_turni_prenotazioni.application.dto.StanzaDTO;
import com.majorbit.bozza_proj_turni_prenotazioni.application.mapper.StanzaMapper;
import com.majorbit.bozza_proj_turni_prenotazioni.domain.model.Stanza;
import com.majorbit.bozza_proj_turni_prenotazioni.domain.repository.PianoRepository;
import com.majorbit.bozza_proj_turni_prenotazioni.domain.repository.StanzaRepository;
import com.majorbit.bozza_proj_turni_prenotazioni.util.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GestioneStanza {

    private final StanzaRepository stanzaRepository;
    private final PianoRepository pianoRepository;
    private final StanzaMapper stanzaMapper;

    public StanzaDTO createStanza(StanzaDTO StanzaDTO) {
        var stanza = stanzaMapper.toEntity(StanzaDTO);
        var savedStanza = stanzaRepository.save(stanza);
        return stanzaMapper.toDTO(savedStanza);
    }

    public StanzaDTO getStanzaById(Integer id) {
        var stanza = stanzaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Resource not found"));
        return stanzaMapper.toDTO(stanza);
    }

    public List<StanzaDTO> getAllStanze() {
        List<Stanza> stanze = stanzaRepository.findAll();
        return stanze.stream().map(stanzaMapper::toDTO).collect(Collectors.toList());
    }

    public StanzaDTO updateStanza(Integer id, StanzaDTO stanzaDTO) {
        var stanza = stanzaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Resource not found"));
        stanza.setNome(stanzaDTO.getNome());
        stanza.setCapienza(stanzaDTO.getCapienza());
        var piano = pianoRepository.findById(stanzaDTO.getPiano()).orElseThrow(() -> new ResourceNotFoundException("Resource not found"));
        stanza.setPiano(piano);
        var updatedStanza = stanzaRepository.save(stanza);
        return stanzaMapper.toDTO(updatedStanza);
    }

    public void deleteStanza(Integer id) {
        var stanza = stanzaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Resource not found"));
        stanzaRepository.delete(stanza);
    }
}
