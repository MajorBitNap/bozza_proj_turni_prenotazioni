package com.majorbit.bozza_proj_turni_prenotazioni.application.usecases.impl;

import com.majorbit.bozza_proj_turni_prenotazioni.application.dto.PostoDTO;
import com.majorbit.bozza_proj_turni_prenotazioni.application.mapper.PostoMapper;
import com.majorbit.bozza_proj_turni_prenotazioni.application.usecases.spec.RecuperaPostiDisponibili;
import com.majorbit.bozza_proj_turni_prenotazioni.domain.model.Posto;
import com.majorbit.bozza_proj_turni_prenotazioni.domain.repository.PostoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class IRecuperaPostiDisponibili implements RecuperaPostiDisponibili {

    private final PostoRepository postoRepository;
    private final PostoMapper postoMapper;

    public List<PostoDTO> getPostiDisponibili() {
        List<Posto> postiDisponibili = postoRepository.getPostiDisponibili();
        List<PostoDTO> postiDiponibiliDTO = new ArrayList<>();
        for (int i = 0; i < postiDisponibili.size(); i++) {
            PostoDTO postoDTO = postoMapper.toDTO(postiDisponibili.get(i));
            postiDiponibiliDTO.add(i, postoDTO);
        }
        return postiDiponibiliDTO;
    }
}
