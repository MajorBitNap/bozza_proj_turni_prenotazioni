package com.majorbit.bozza_proj_turni_prenotazioni.application.usecases.spec;

import com.majorbit.bozza_proj_turni_prenotazioni.application.dto.PianoDTO;

import java.util.List;

public interface GestionePiano {
    PianoDTO createPiano(PianoDTO pianoDTO);
    PianoDTO getPianoById(Long id);
    List<PianoDTO> getAllPiani();
    PianoDTO updatePiano(Long id, PianoDTO pianoDTO);
    void deletePiano(Long id);
}
