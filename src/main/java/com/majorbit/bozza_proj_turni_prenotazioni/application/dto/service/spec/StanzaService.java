package com.majorbit.bozza_proj_turni_prenotazioni.application.dto.service.spec;

import com.majorbit.bozza_proj_turni_prenotazioni.application.dto.StanzaDTO;

import java.util.List;

public interface StanzaService {
    StanzaDTO createStanza(StanzaDTO stanzaDTO);
    StanzaDTO getStanzaById(Long id);
    List<StanzaDTO> getAllStanze();
    StanzaDTO updateStanza(Long id, StanzaDTO stanzaDTO);
    void deleteStanza(Long id);
}
