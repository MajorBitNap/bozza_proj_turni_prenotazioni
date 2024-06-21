package com.majorbit.bozza_proj_turni_prenotazioni.application.usecases.spec;

import com.majorbit.bozza_proj_turni_prenotazioni.application.dto.PrenotazioneDTO;
import org.springframework.stereotype.Component;

@Component
public interface ApprovaPrenotazione {
    public PrenotazioneDTO approvaPrenotazione(Integer id);
}
