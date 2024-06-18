package com.majorbit.bozza_proj_turni_prenotazioni.application.usecases.spec;

import com.majorbit.bozza_proj_turni_prenotazioni.application.dto.PostoDTO;
import com.majorbit.bozza_proj_turni_prenotazioni.application.dto.PrenotazioneDTO;
import com.majorbit.bozza_proj_turni_prenotazioni.application.dto.UtenteDTO;

import java.sql.Date;

public interface PrenotazioneFissa {

    public PrenotazioneDTO prenotazioneFissa(Date dataInizio, UtenteDTO utenteDTO, PostoDTO postoDTO);

}
