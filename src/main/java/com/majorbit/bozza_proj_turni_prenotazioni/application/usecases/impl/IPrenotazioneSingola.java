package com.majorbit.bozza_proj_turni_prenotazioni.application.usecases.impl;

import com.majorbit.bozza_proj_turni_prenotazioni.application.dto.PrenotazioneDTO;
import com.majorbit.bozza_proj_turni_prenotazioni.application.mapper.PrenotazioneMapper;
import com.majorbit.bozza_proj_turni_prenotazioni.application.service.EmailService;
import com.majorbit.bozza_proj_turni_prenotazioni.application.usecases.spec.PrenotazioneSingola;
import com.majorbit.bozza_proj_turni_prenotazioni.domain.model.Prenotazione;
import com.majorbit.bozza_proj_turni_prenotazioni.domain.repository.PostoRepository;
import com.majorbit.bozza_proj_turni_prenotazioni.domain.repository.PrenotazioneRepository;
import com.majorbit.bozza_proj_turni_prenotazioni.domain.repository.UtenteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class IPrenotazioneSingola implements PrenotazioneSingola {

    private final PrenotazioneRepository prenotazioneRepository;
    private final UtenteRepository utenteRepository;
    private final PostoRepository postoRepository;
    private final PrenotazioneMapper prenotazioneMapper;
    private final EmailService emailService;

    @Override
    public PrenotazioneDTO creaPrenotazione(PrenotazioneDTO prenotazioneDTO){
        var prenotazione = new Prenotazione();
        var utente = utenteRepository.findById(prenotazioneDTO.getUtente()).orElseThrow();
        var posto = postoRepository.findById(prenotazioneDTO.getPosto()).orElseThrow();
        prenotazione.setDataInizio(prenotazioneDTO.getDataInizio());
        prenotazione.setDataFine(prenotazioneDTO.getDataFine());
        prenotazione.setStato("INSERITA");
        prenotazione.setUtente(utente);
        prenotazione.setPosto(posto);
        prenotazioneRepository.save(prenotazione);
        emailService.sendEmail(
                utente.getEmail(),
                "Prenotazione Singola Effettuata",
                "E' stata inserita una prenotazione per il " + utente.getRuolo().toString().toLowerCase()
                        + " " + utente.getNome() + " " + utente.getCognome() + " nel giorno "
                        + prenotazioneDTO.getDataInizio());
        return prenotazioneMapper.toDTO(prenotazione);
    }
}
