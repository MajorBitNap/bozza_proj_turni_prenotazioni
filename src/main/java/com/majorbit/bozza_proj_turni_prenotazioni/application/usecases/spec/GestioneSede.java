package com.majorbit.bozza_proj_turni_prenotazioni.application.usecases.spec;

import com.majorbit.bozza_proj_turni_prenotazioni.application.dto.SedeDTO;

import java.util.List;

public interface GestioneSede {
    SedeDTO createSede(SedeDTO sedeDTO);
    SedeDTO getSedeById(Long id);
    List<SedeDTO> getAllSedi();
    SedeDTO updateSede(Long id, SedeDTO sedeDTO);
    void deleteSede(Long id);
}
