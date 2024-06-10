package com.majorbit.bozza_proj_turni_prenotazioni.application.service.spec;

import com.majorbit.bozza_proj_turni_prenotazioni.application.dto.PrenotazioneDTO;

import java.util.List;

public interface PrenotazioneService {
    PrenotazioneDTO createPrenotazione(PrenotazioneDTO prenotazioneDTO);
    PrenotazioneDTO getPrenotazioneById(Long id);
    List<PrenotazioneDTO> getAllPrenotazioni();
    PrenotazioneDTO updatePrenotazione(Long id, PrenotazioneDTO prenotazioneDTO);
    void deletePrenotazione(Long id);
}
