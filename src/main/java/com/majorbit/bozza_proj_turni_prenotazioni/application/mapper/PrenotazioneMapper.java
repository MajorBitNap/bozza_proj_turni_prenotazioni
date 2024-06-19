package com.majorbit.bozza_proj_turni_prenotazioni.application.mapper;

import com.majorbit.bozza_proj_turni_prenotazioni.application.dto.PrenotazioneDTO;
import com.majorbit.bozza_proj_turni_prenotazioni.domain.model.Prenotazione;
import com.majorbit.bozza_proj_turni_prenotazioni.domain.repository.PostoRepository;
import com.majorbit.bozza_proj_turni_prenotazioni.domain.repository.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PrenotazioneMapper {

    private static UtenteRepository utenteRepository;

    private static PostoRepository postoRepository;

    @Autowired
    public PrenotazioneMapper(UtenteRepository utenteRepository, PostoRepository postoRepository) {
        this.utenteRepository = utenteRepository;
        this.postoRepository = postoRepository;
    }

    public static PrenotazioneDTO toDTO(Prenotazione prenotazione) {
        return new PrenotazioneDTO(
                prenotazione.getDataInizio(),
                prenotazione.getDataFine(),
                prenotazione.getStato(),
                prenotazione.getPosto().getId(),
                prenotazione.getUtente().getId()
        );
    }

    public static Prenotazione toEntity(PrenotazioneDTO prenotazioneDTO) {
        Prenotazione prenotazione = new Prenotazione();
        prenotazione.setDataInizio(prenotazioneDTO.getDataInizio());
        prenotazione.setDataFine(prenotazioneDTO.getDataFine());
        prenotazione.setStato(prenotazioneDTO.getStato());
        prenotazione.setUtente(utenteRepository.findById(prenotazioneDTO.getUtente()).orElseThrow());
        prenotazione.setPosto(postoRepository.findById(prenotazioneDTO.getPosto()).orElseThrow());
        return prenotazione;
    }
}