package com.majorbit.bozza_proj_turni_prenotazioni.application.service.impl;

import com.majorbit.bozza_proj_turni_prenotazioni.application.dto.PostoDTO;
import com.majorbit.bozza_proj_turni_prenotazioni.application.mapper.PostoMapper;
import com.majorbit.bozza_proj_turni_prenotazioni.application.service.spec.PostoService;
import com.majorbit.bozza_proj_turni_prenotazioni.domain.model.Posto;
import com.majorbit.bozza_proj_turni_prenotazioni.domain.repository.PostoRepository;
import com.majorbit.bozza_proj_turni_prenotazioni.util.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class IPostoService implements PostoService {

    @Autowired
    private PostoRepository postoRepository;

    @Autowired
    private PostoMapper postoMapper;

    @Override
    public PostoDTO createPosto(PostoDTO postoDTO) {
        Posto posto = postoMapper.toEntity(postoDTO);
        Posto savedPosto = postoRepository.save(posto);
        return postoMapper.toDTO(savedPosto);
    }

    @Override
    public PostoDTO getPostoById(Long id) {
        Posto posto = postoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Posto not found"));
        return postoMapper.toDTO(posto);
    }

    @Override
    public List<PostoDTO> getAllPosti() {
        List<Posto> posti = postoRepository.findAll();
        return posti.stream().map(postoMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public PostoDTO updatePosto(Long id, PostoDTO postoDTO) {
        Posto posto = postoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Posto not found"));
        posto.setId(postoDTO.id());
        posto.setNome(postoDTO.nome());
        posto.setStanzaId(postoDTO.stanzaId());
        Posto updatedPosto = postoRepository.save(posto);
        return postoMapper.toDTO(updatedPosto);
    }

    @Override
    public void deletePosto(Long id) {
        Posto posto = postoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Posto not found"));
        postoRepository.delete(posto);
    }
}
