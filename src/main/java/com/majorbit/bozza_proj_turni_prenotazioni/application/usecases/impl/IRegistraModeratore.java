package com.majorbit.bozza_proj_turni_prenotazioni.application.usecases.impl;

import com.majorbit.bozza_proj_turni_prenotazioni.application.dto.UtenteDTO;
import com.majorbit.bozza_proj_turni_prenotazioni.application.service.GestioneUtente;
import com.majorbit.bozza_proj_turni_prenotazioni.application.usecases.spec.RegistraModeratore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IRegistraModeratore implements RegistraModeratore {

    private final GestioneUtente gestioneUtente;

    @Autowired
    public IRegistraModeratore(GestioneUtente gestioneUtente) {
        this.gestioneUtente = gestioneUtente;
    }

    //  [per l'amministratore] logica per registrare un utente con ruolo MODERATORE
    @Override
    public void registraModeratore(UtenteDTO UtenteDTO) {
        gestioneUtente.createUtente(UtenteDTO);
    }
}
