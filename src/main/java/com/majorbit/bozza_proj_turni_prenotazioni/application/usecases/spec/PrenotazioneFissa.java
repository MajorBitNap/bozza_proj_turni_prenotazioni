package com.majorbit.bozza_proj_turni_prenotazioni.application.usecases.spec;

import com.majorbit.bozza_proj_turni_prenotazioni.application.dto.PrenotazioneDTO;

import java.util.List;

public interface PrenotazioneFissa {

    public List<PrenotazioneDTO> creaPrenotazioniRicorrenti(PrenotazioneDTO prenotazioneDTO);

}
