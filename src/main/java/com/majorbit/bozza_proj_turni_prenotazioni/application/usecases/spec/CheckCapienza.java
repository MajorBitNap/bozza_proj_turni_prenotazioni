package com.majorbit.bozza_proj_turni_prenotazioni.application.usecases.spec;

import com.majorbit.bozza_proj_turni_prenotazioni.application.dto.StanzaDTO;
import org.springframework.stereotype.Component;

import java.sql.Date;
@Component
public interface CheckCapienza {
    public boolean isOver(StanzaDTO stanzaDTO, Date dataInizio, Date  datafine);
}
