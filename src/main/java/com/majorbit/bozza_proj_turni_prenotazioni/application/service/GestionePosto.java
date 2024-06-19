package com.majorbit.bozza_proj_turni_prenotazioni.application.service;

import com.majorbit.bozza_proj_turni_prenotazioni.application.mapper.PostoMapper;
import com.majorbit.bozza_proj_turni_prenotazioni.domain.model.Posto;
import com.majorbit.bozza_proj_turni_prenotazioni.domain.repository.PostoRepository;
import com.majorbit.bozza_proj_turni_prenotazioni.util.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GestionePosto {

    @Autowired
    private PostoRepository postoRepository;

    @Autowired
    private PostoMapper postoMapper;

    public com.majorbit.bozza_proj_turni_prenotazioni.application.dto.PostoDTO createPosto(com.majorbit.bozza_proj_turni_prenotazioni.application.dto.PostoDTO postoDTO) {
        Posto posto = PostoMapper.toEntity(postoDTO);
        Posto savedPosto = postoRepository.save(posto);
        return PostoMapper.toDTO(savedPosto);
    }

    public com.majorbit.bozza_proj_turni_prenotazioni.application.dto.PostoDTO getPostoById(Long id) {
        Posto posto = postoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Posto not found"));
        return PostoMapper.toDTO(posto);
    }

    public List<com.majorbit.bozza_proj_turni_prenotazioni.application.dto.PostoDTO> getAllPosti() {
        List<Posto> posti = postoRepository.findAll();
        return posti.stream().map(PostoMapper::toDTO).collect(Collectors.toList());
    }

    public com.majorbit.bozza_proj_turni_prenotazioni.application.dto.PostoDTO updatePosto(Long id, com.majorbit.bozza_proj_turni_prenotazioni.application.dto.PostoDTO postoDTO) {
        Posto posto = postoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Posto not found"));
        posto.setNome(postoDTO.getNome());
        posto.setStanza(postoDTO.getStanza());
        Posto updatedPosto = postoRepository.save(posto);
        return PostoMapper.toDTO(updatedPosto);
    }

    public void deletePosto(Long id) {
        Posto posto = postoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Posto not found"));
        postoRepository.delete(posto);
    }
}
