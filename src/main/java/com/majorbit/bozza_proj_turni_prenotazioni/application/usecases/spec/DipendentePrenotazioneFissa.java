package com.majorbit.bozza_proj_turni_prenotazioni.application.usecases.spec;

import com.majorbit.bozza_proj_turni_prenotazioni.application.dto.PrenotazioneDTO;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface DipendentePrenotazioneFissa {
    List<PrenotazioneDTO> creaPrenotazioniRicorrenti(PrenotazioneDTO PrenotazioneDTO);
}
