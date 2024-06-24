package com.majorbit.bozza_proj_turni_prenotazioni.application.usecases.impl;

import com.majorbit.bozza_proj_turni_prenotazioni.application.dto.UtenteDTO;
import com.majorbit.bozza_proj_turni_prenotazioni.application.service.GestioneUtente;
import com.majorbit.bozza_proj_turni_prenotazioni.application.usecases.spec.RegistraDipendente;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class IRegistraDipendente implements RegistraDipendente {

    private final GestioneUtente gestioneUtente;

    //  [per l'amministratore] logica per registrare un utente con ruolo DIPENDENTE
    @Override
    public void registraDipendente(UtenteDTO UtenteDTO) {
        gestioneUtente.createUtente(UtenteDTO);
    }
}
