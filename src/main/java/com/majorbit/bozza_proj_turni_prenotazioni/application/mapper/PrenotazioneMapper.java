package com.majorbit.bozza_proj_turni_prenotazioni.application.mapper;

import com.majorbit.bozza_proj_turni_prenotazioni.application.dto.PrenotazioneDTO;
import com.majorbit.bozza_proj_turni_prenotazioni.domain.model.Prenotazione;
import org.springframework.stereotype.Component;

@Component
public class PrenotazioneMapper {

    public static PrenotazioneDTO toDTO(Prenotazione prenotazione) {
        return new PrenotazioneDTO(
                prenotazione.getDataInizio(),
                prenotazione.getDataFine(),
                prenotazione.getStato(),
                PostoMapper.toDTO(prenotazione.getPosto()),
                UtenteMapper.toDTO(prenotazione.getUtente())
        );
    }

    public static Prenotazione toEntity(PrenotazioneDTO PrenotazioneDTO) {
        Prenotazione prenotazione = new Prenotazione();
        prenotazione.setDataInizio(PrenotazioneDTO.getDataInizio());
        prenotazione.setDataFine(PrenotazioneDTO.getDataFine());
        prenotazione.setStato(PrenotazioneDTO.getStato());
        prenotazione.setUtente(UtenteMapper.toEntity(PrenotazioneDTO.getUtente()));
        prenotazione.setPosto(PostoMapper.toEntity(PrenotazioneDTO.getPosto()));
        return prenotazione;
    }
}