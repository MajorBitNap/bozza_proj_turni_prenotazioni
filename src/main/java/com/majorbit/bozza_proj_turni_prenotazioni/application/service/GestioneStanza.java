package com.majorbit.bozza_proj_turni_prenotazioni.application.service;

import com.majorbit.bozza_proj_turni_prenotazioni.application.dto.StanzaDTO;
import com.majorbit.bozza_proj_turni_prenotazioni.application.mapper.StanzaMapper;
import com.majorbit.bozza_proj_turni_prenotazioni.domain.model.Stanza;
import com.majorbit.bozza_proj_turni_prenotazioni.domain.repository.StanzaRepository;
import com.majorbit.bozza_proj_turni_prenotazioni.util.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GestioneStanza {

    @Autowired
    private StanzaRepository stanzaRepository;

    @Autowired
    private StanzaMapper stanzaMapper;

    public StanzaDTO createStanza(StanzaDTO stanzaDTO) {
        Stanza stanza = StanzaMapper.toEntity(stanzaDTO);
        Stanza savedStanza = stanzaRepository.save(stanza);
        return StanzaMapper.toDTO(savedStanza);
    }

    public StanzaDTO getStanzaById(Long id) {
        Stanza stanza = stanzaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Stanza not found"));
        return StanzaMapper.toDTO(stanza);
    }

    public List<StanzaDTO> getAllStanze() {
        List<Stanza> stanze = stanzaRepository.findAll();
        return stanze.stream().map(StanzaMapper::toDTO).collect(Collectors.toList());
    }

    public StanzaDTO updateStanza(Long id, StanzaDTO stanzaDTO) {
        Stanza stanza = stanzaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Stanza not found"));
        stanza.setId(stanzaDTO.getId());
        stanza.setNome(stanzaDTO.getNome());
        stanza.setCapienza(stanzaDTO.getCapienza());
        stanza.setPiano(stanzaDTO.getPiano());
        Stanza updatedStanza = stanzaRepository.save(stanza);
        return StanzaMapper.toDTO(updatedStanza);
    }

    public void deleteStanza(Long id) {
        Stanza stanza = stanzaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Stanza not found"));
        stanzaRepository.delete(stanza);
    }
}
