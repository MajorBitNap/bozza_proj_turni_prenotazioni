package com.majorbit.bozza_proj_turni_prenotazioni.application.usecases.spec;

import com.majorbit.bozza_proj_turni_prenotazioni.application.dto.PrenotazioneDTO;

import java.util.List;

public interface GestionePrenotazione {
    PrenotazioneDTO createPrenotazione(PrenotazioneDTO prenotazioneDTO);
    PrenotazioneDTO getPrenotazioneById(Long id);
    List<PrenotazioneDTO> getAllPrenotazioni();
    PrenotazioneDTO updatePrenotazione(Long id, PrenotazioneDTO prenotazioneDTO);
    void deletePrenotazione(Long id);
}
