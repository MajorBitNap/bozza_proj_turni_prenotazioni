package com.majorbit.bozza_proj_turni_prenotazioni.application.dto.service.spec;

import com.majorbit.bozza_proj_turni_prenotazioni.application.dto.PostoDTO;

import java.util.List;

public interface PostoService {
    PostoDTO createPosto(PostoDTO postoDTO);
    PostoDTO getPostoById(Long id);
    List<PostoDTO> getAllPosti();
    PostoDTO updatePosto(Long id, PostoDTO postoDTO);
    void deletePosto(Long id);
}
