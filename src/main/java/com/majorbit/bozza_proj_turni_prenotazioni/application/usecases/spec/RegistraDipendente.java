package com.majorbit.bozza_proj_turni_prenotazioni.application.usecases.spec;

import com.majorbit.bozza_proj_turni_prenotazioni.application.dto.UtenteDTO;
import org.springframework.stereotype.Component;

@Component
public interface RegistraDipendente {
    void registraDipendente(UtenteDTO UtenteDTO);
}
