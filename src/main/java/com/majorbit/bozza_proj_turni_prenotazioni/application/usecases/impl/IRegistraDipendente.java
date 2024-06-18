package com.majorbit.bozza_proj_turni_prenotazioni.application.usecases.impl;

import com.majorbit.bozza_proj_turni_prenotazioni.application.dto.UtenteDTO;
import com.majorbit.bozza_proj_turni_prenotazioni.application.usecases.spec.RegistraDipendente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IRegistraDipendente implements RegistraDipendente {

    @Autowired
    private IGestioneUtente iGestioneUtente;

    @Override
    public void registraDipendente(UtenteDTO utenteDTO) {
        iGestioneUtente.createUtente(utenteDTO);
    }
}
