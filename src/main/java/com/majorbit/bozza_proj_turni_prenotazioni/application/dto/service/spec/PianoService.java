package com.majorbit.bozza_proj_turni_prenotazioni.application.dto.service.spec;

import com.majorbit.bozza_proj_turni_prenotazioni.application.dto.PianoDTO;

import java.util.List;

public interface PianoService {
    PianoDTO createPiano(PianoDTO pianoDTO);
    PianoDTO getPianoById(Long id);
    List<PianoDTO> getAllPiani();
    PianoDTO updatePiano(Long id, PianoDTO pianoDTO);
    void deletePiano(Long id);
}
