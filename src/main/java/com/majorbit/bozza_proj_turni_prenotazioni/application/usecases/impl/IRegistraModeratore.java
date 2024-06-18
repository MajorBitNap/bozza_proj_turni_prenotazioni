package com.majorbit.bozza_proj_turni_prenotazioni.application.usecases.impl;

import com.majorbit.bozza_proj_turni_prenotazioni.application.dto.UtenteDTO;
import com.majorbit.bozza_proj_turni_prenotazioni.application.usecases.spec.RegistraModeratore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IRegistraModeratore implements RegistraModeratore {

    @Autowired
    private IGestioneUtente iGestioneUtente;

    @Override
    public void registraModeratore(UtenteDTO utenteDTO) {
        iGestioneUtente.createUtente(utenteDTO);
    }
}
