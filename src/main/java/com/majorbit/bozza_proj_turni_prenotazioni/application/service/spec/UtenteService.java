package com.majorbit.bozza_proj_turni_prenotazioni.application.service.spec;

import com.majorbit.bozza_proj_turni_prenotazioni.application.dto.UtenteDTO;

import java.util.List;

public interface UtenteService {
    UtenteDTO createUtente(UtenteDTO utenteDTO);
    UtenteDTO getUtenteById(Long id);
    List<UtenteDTO> getAllUtenti();
    UtenteDTO updateUtente(Long id, UtenteDTO utenteDTO);
    void deleteUtente(Long id);
}
