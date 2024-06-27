package com.majorbit.bozza_proj_turni_prenotazioni.application.service;

import com.majorbit.bozza_proj_turni_prenotazioni.application.dto.PostoDTO;
import com.majorbit.bozza_proj_turni_prenotazioni.application.mapper.PostoMapper;
import com.majorbit.bozza_proj_turni_prenotazioni.domain.model.Posto;
import com.majorbit.bozza_proj_turni_prenotazioni.domain.repository.PostoRepository;
import com.majorbit.bozza_proj_turni_prenotazioni.domain.repository.StanzaRepository;
import com.majorbit.bozza_proj_turni_prenotazioni.util.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GestionePosto {

    private final PostoRepository postoRepository;
    private final StanzaRepository stanzaRepository;
    private final PostoMapper postoMapper;

    public PostoDTO createPosto(PostoDTO postoDTO) {
        var posto = postoMapper.toEntity(postoDTO);
        var savedPosto = postoRepository.save(posto);
        return postoMapper.toDTO(savedPosto);
    }

    public PostoDTO getPostoById(Integer id) {
        var posto = postoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("resources not found"));
        return postoMapper.toDTO(posto);
    }

    public List<PostoDTO> getAllPosti() {
        List<Posto> posti = postoRepository.findAll();
        return posti.stream().map(postoMapper::toDTO).collect(Collectors.toList());
    }

    public PostoDTO updatePosto(Integer id, PostoDTO postoDTO) {
        var posto = postoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("resources not found"));
        posto.setNome(postoDTO.getNome());
        posto.setStanza(stanzaRepository.findById(postoDTO.getStanza()).orElseThrow(() -> new ResourceNotFoundException("resources not found")));
        var updatedPosto = postoRepository.save(posto);
        return postoMapper.toDTO(updatedPosto);
    }

    public void deletePosto(Integer id) {
        var posto = postoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("resources not found"));
        postoRepository.delete(posto);
    }
}
