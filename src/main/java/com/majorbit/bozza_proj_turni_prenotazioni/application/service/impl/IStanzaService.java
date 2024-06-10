package com.majorbit.bozza_proj_turni_prenotazioni.application.service.impl;

import com.majorbit.bozza_proj_turni_prenotazioni.application.dto.StanzaDTO;
import com.majorbit.bozza_proj_turni_prenotazioni.application.mapper.StanzaMapper;
import com.majorbit.bozza_proj_turni_prenotazioni.application.service.spec.StanzaService;
import com.majorbit.bozza_proj_turni_prenotazioni.domain.model.Stanza;
import com.majorbit.bozza_proj_turni_prenotazioni.domain.repository.StanzaRepository;
import com.majorbit.bozza_proj_turni_prenotazioni.util.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class IStanzaService implements StanzaService {

    @Autowired
    private StanzaRepository stanzaRepository;

    @Autowired
    private StanzaMapper stanzaMapper;

    @Override
    public StanzaDTO createStanza(StanzaDTO stanzaDTO) {
        Stanza stanza = StanzaMapper.toEntity(stanzaDTO);
        Stanza savedStanza = stanzaRepository.save(stanza);
        return StanzaMapper.toDTO(savedStanza);
    }

    @Override
    public StanzaDTO getStanzaById(Long id) {
        Stanza stanza = stanzaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Stanza not found"));
        return StanzaMapper.toDTO(stanza);
    }

    @Override
    public List<StanzaDTO> getAllStanze() {
        List<Stanza> stanze = stanzaRepository.findAll();
        return stanze.stream().map(StanzaMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public StanzaDTO updateStanza(Long id, StanzaDTO stanzaDTO) {
        Stanza stanza = stanzaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Stanza not found"));
        stanza.setId(stanzaDTO.id());
        stanza.setNome(stanzaDTO.nome());
        stanza.setCapienza(stanzaDTO.capienza());
        stanza.setPianoId(stanzaDTO.pianoId());
        Stanza updatedStanza = stanzaRepository.save(stanza);
        return StanzaMapper.toDTO(updatedStanza);
    }

    @Override
    public void deleteStanza(Long id) {
        Stanza stanza = stanzaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Stanza not found"));
        stanzaRepository.delete(stanza);
    }
}
