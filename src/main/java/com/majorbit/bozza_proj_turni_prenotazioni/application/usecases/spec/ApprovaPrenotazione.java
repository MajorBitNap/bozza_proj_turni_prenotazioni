package com.majorbit.bozza_proj_turni_prenotazioni.application.usecases.spec;

import com.majorbit.bozza_proj_turni_prenotazioni.application.dto.PrenotazioneDTO;

public interface ApprovaPrenotazione {
    public PrenotazioneDTO approvaPrenotazione(Long id);
}
