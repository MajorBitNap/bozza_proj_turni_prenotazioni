package com.majorbit.bozza_proj_turni_prenotazioni.application.usecases.spec;

import com.majorbit.bozza_proj_turni_prenotazioni.application.dto.PostoDTO;

import java.util.List;

public interface GestionePosto {
    PostoDTO createPosto(PostoDTO postoDTO);
    PostoDTO getPostoById(Long id);
    List<PostoDTO> getAllPosti();
    PostoDTO updatePosto(Long id, PostoDTO postoDTO);
    void deletePosto(Long id);
}
